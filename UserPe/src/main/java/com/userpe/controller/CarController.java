package com.userpe.controller;

import com.userpe.dto.request.CarRequest;
import com.userpe.dto.response.CarResponse;
import com.userpe.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car/v1")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CarResponse>> findAll() {
        List<CarResponse> carResponseList = carService.getAll();
        return ResponseEntity.ok(carResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findById(@PathVariable Long id) {
        CarResponse dto = carService.getCarById(id);
        return ResponseEntity.ok(dto);

    }

    @PutMapping
    public ResponseEntity<CarResponse> update(@PathVariable Long id,
                                              @RequestBody CarRequest carRequest) {
        CarResponse carResponse = carService.updateCar(id, carRequest);
        return ResponseEntity.ok(carResponse);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
