package max.demo.unittests.service;

import lombok.RequiredArgsConstructor;
import max.demo.unittests.model.Car;
import max.demo.unittests.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car addNewCar(Car car) {

        if (!"BMW".equals(car.getBrand()) && !"AUDI".equals(car.getBrand()) && !"MERCEDES".equals(car.getBrand())) {
            throw new IllegalArgumentException("The brand of the car must be: BMW, AUDI or MERCEDES.");
        }

        if (car.getNumberOfSeats() > 5) {
            throw new IllegalArgumentException("The car has more than 5 seats.");
        }

        return carRepository.saveCar(car);
    }

}
