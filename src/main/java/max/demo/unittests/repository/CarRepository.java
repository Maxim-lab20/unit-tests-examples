package max.demo.unittests.repository;

import max.demo.unittests.model.Car;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Think of this class as a real repository.
 * For demo purposes I did not connect to an actual database.
 */
@Repository
public class CarRepository {

    private List<Car> cars =
            List.of(buildCar("Mercedes", LocalDate.of(2023, Month.JANUARY, 1), "C class",
                            4, 2f),
                    buildCar("BMW", LocalDate.of(2023, Month.JANUARY, 1), "Series 3",
                            2, 3f),
                    buildCar("Audi", LocalDate.of(2023, Month.JANUARY, 1), "A4",
                            4, 1.6f));

    public List<Car> getAllCars() {
        return cars;
    }

    public Car saveCar(Car car) {
        cars.add(car);
        return car;
    }

    private Car buildCar(String brand, LocalDate manufactureYear, String model,
                         Integer numberOfSeats, Float engineCapacity) {
        return Car.builder()
                .brand(brand)
                .manufactureYear(manufactureYear)
                .model(model)
                .numberOfSeats(numberOfSeats)
                .engineCapacity(engineCapacity)
                .build();
    }

}
