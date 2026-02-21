package com.demo.surucu.controller;

import com.demo.surucu.CarResponse;
import com.demo.surucu.service.SurucuServiceRestClient;
import com.userpe.dto.request.CarRequest;
import com.userpe.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rest-client-car")
@RestController
public class SurucuControllerRestClient {

    private final SurucuServiceRestClient surucuServiceRestClient;

    public SurucuControllerRestClient(SurucuServiceRestClient surucuServiceRestClient) {
        this.surucuServiceRestClient = surucuServiceRestClient;
    }

    @GetMapping("/toEntity/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id) {
        ResponseEntity<CarResponse> car = surucuServiceRestClient.getCar(id);
        return car;
    }

    @GetMapping("/body/{id}")
    public ResponseEntity<CarResponse> getCar2(@PathVariable Long id) {
        CarResponse carResponse = surucuServiceRestClient.getCar2(id);
        return ResponseEntity.ok(carResponse);
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        ResponseEntity<List<CarResponse>> responseEntity = surucuServiceRestClient.getAllCar();
        return responseEntity;
    }

    @PostMapping("/add1")
    public ResponseEntity<CarResponse> addCar(CarRequest carRequest) {
        return surucuServiceRestClient.addCar(carRequest);
    }

    @PostMapping("/add2")
    public ResponseEntity<CarResponse> addCar2(CarRequest carRequest) {
        CarResponse carResponse = surucuServiceRestClient.addCar2(carRequest);
        return ResponseEntity.ok(carResponse);
    }

    @PostMapping("/add3")
    public ResponseEntity<?> addCar3(CarRequest carRequest) {
         surucuServiceRestClient.addCar3(carRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body("eklendi");
    }

    @PutMapping
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return surucuServiceRestClient.updateCar(id, carRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        surucuServiceRestClient.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("silindi");
    }





}
