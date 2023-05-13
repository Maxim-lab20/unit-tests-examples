package max.demo.unittests.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import max.demo.unittests.model.Car;
import max.demo.unittests.service.CarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("app-name", "max-app");
        return new ResponseEntity<>(carService.getAllCars(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addNewCar(@RequestBody @NonNull Car car) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.addNewCar(car));
    }
}
