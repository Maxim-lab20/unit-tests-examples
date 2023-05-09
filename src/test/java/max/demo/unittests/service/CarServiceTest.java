package max.demo.unittests.service;

import com.sun.jdi.InternalException;
import max.demo.unittests.model.Car;
import max.demo.unittests.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Test
    void getAllCars_success() {
        // GIVEN
        List<Car> cars = List.of(Car.builder()
                .brand("Opel")
                .manufactureYear(LocalDate.of(2006, Month.JANUARY, 1))
                .model("Meriva")
                .numberOfSeats(4)
                .engineCapacity(1.3f)
                .build());


        when(carRepository.getAllCars())
                .thenReturn(cars);

        // WHEN
        List<Car> actualResponse = carService.getAllCars();

        // THEN
        assertThat(actualResponse)
                .isEqualTo(cars);

    }

    @Test
    void addNewCar_success() {
        // GIVEN
        Car car = Car.builder()
                .brand("BMW")
                .numberOfSeats(2)
                .build();


        when(carRepository.saveCar(car))
                .thenReturn(car);

        // WHEN
        Car actualResponse = carService.addNewCar(car);

        // THEN
        assertThat(actualResponse)
                .isEqualTo(car);
    }

    @Test
    void addNewCar_unknownBrandError() {
        // GIVEN
        Car car = Car.builder()
                .brand("OPEL")
                .build();

        // WHEN + THEN
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> carService.addNewCar(car))
                .withMessage("The brand of the car must be: BMW, AUDI or MERCEDES.");
    }

    @Test
    void addNewCar_tooManySeatsError() {
        // GIVEN
        Car car = Car.builder()
                .brand("AUDI")
                .numberOfSeats(8)
                .build();

        // WHEN + THEN
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> carService.addNewCar(car))
                .withMessage("The car has more than 5 seats.");
    }

    @Test
    void addNewCar_unexpectedException() {
        // GIVEN
        Car car = Car.builder()
                .brand("AUDI")
                .numberOfSeats(4)
                .build();

        when(carRepository.saveCar(car))
                .thenThrow(new InternalException("Unexpected error"));

        // WHEN + THEN
        assertThatExceptionOfType(InternalException.class)
                .isThrownBy(() -> carService.addNewCar(car))
                .withMessage("Unexpected error");
    }

}