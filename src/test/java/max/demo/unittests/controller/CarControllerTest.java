package max.demo.unittests.controller;

import max.demo.unittests.model.Car;
import max.demo.unittests.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit test class for CarController class.
 * getAllCars_1() and getAllCars_2() do the exact same thing even though the code is doing it in different ways.
 * Besides how a unit test can be written I want to show you that there are many ways in which one unit test can be written.
 * From my point of view, as long as the code is clean, easy to follow, and it does the job wel, it's keeper.
 */
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @InjectMocks
    CarController carController; // don't bother using public, private etc. It's not relevant

    @Mock
    CarService carService;

    /**
     * Don't bother about public, private etc. It's not a relevant thing in our unit tests.
     * You can use a ton of different names. As long as they are suggestive they're good.
     * examples: getAllCars_success(), getAllCarsSuccess(), getAllCarsWithSuccess() - they all suggest the same thing
     * The key is TO BE CONSISTENT in you namings.
     */
    @Test
    void getAllCars_1() {
        // GIVEN
        List<Car> cars = List.of(Car.builder()
                .brand("Opel")
                .manufactureYear(LocalDate.of(2006, Month.JANUARY, 1))
                .model("Meriva")
                .numberOfSeats(4)
                .engineCapacity(1.3f)
                .build());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("app-name", "max-app");
        ResponseEntity<List<Car>> expectedResponse =
                new ResponseEntity<>(cars, httpHeaders, HttpStatus.OK);

        when(carService.getAllCars())
                .thenReturn(cars);

        // WHEN
        ResponseEntity<List<Car>> actualResponse = carController.getAllCars();

        // THEN
        assertThat(actualResponse)
                .isEqualTo(expectedResponse);

    }

    @Test
    void getAllCars_2() {
        // GIVEN
        List<Car> cars = List.of(Car.builder()
                .brand("Opel")
                .manufactureYear(LocalDate.of(2006, Month.JANUARY, 1))
                .model("Meriva")
                .numberOfSeats(4)
                .engineCapacity(1.3f)
                .build());

        HttpHeaders expectedHttpHeaders = new HttpHeaders();
        expectedHttpHeaders.add("app-name", "max-app");

        when(carService.getAllCars())
                .thenReturn(cars);

        // WHEN
        ResponseEntity<List<Car>> actualResponse = carController.getAllCars();

        // THEN
        assertThat(actualResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getHeaders())
                .isEqualTo(expectedHttpHeaders);
        assertThat(actualResponse.getBody())
                .isEqualTo(cars);
    }

}