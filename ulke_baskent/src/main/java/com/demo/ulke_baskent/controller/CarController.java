package com.demo.ulke_baskent.controller;

import com.demo.ulke_baskent.dto.request.CarRequest;
import com.demo.ulke_baskent.dto.response.CarResponse;
import com.demo.ulke_baskent.entity.Car;
import com.demo.ulke_baskent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/car/v1")
public class CarController {

    private CarService carService;

    public  CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarResponse> addCar(@RequestBody CarRequest  carRequest) {
        CarResponse carResponse = carService.addCar(carRequest);
        return ResponseEntity.ok().body(carResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id){
        CarResponse carResponse = carService.getCarById(id);
        return ResponseEntity.ok().body(carResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        CarResponse carResponse = (CarResponse) carService.getAllCars();
        return ResponseEntity.ok().body(Collections.singletonList(carResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        CarResponse carResponse = carService.updateCar(id, carRequest);
        return ResponseEntity.ok().body(carResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}






















