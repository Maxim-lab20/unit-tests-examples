package max.demo.unittests.service;

import lombok.RequiredArgsConstructor;
import max.demo.unittests.model.Car;
import max.demo.unittests.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    /* this field is injected by constructor,
     @RequiredArgsConstructor creates a constructor that contains all "final" fields */
    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

}
