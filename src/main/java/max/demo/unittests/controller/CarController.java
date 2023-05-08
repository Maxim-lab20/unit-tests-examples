package max.demo.unittests.controller;

import lombok.RequiredArgsConstructor;
import max.demo.unittests.model.Car;
import max.demo.unittests.service.CarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        /*
            I usually don't like having any logic inside constructor class.
            I don't know if this is a good practice or not. It's more like a preference.
            I only do that when I have no other options only.
            I do it now for demo purposes.
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("app-name", "max-app");

        return new ResponseEntity<>(carService.getAllCars(), httpHeaders, HttpStatus.OK);
    }
}
