package com.demo.carapi.controller;

import com.demo.carapi.dto.request.CarRequestDto;
import com.demo.carapi.dto.response.CarResponseDto;
import com.demo.carapi.service.CarService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add-car")
    public ResponseEntity<CarResponseDto> addCar(@RequestBody CarRequestDto carRequestDto) {
        CarResponseDto response = carService.save(carRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-car/{id}")
    public ResponseEntity<CarResponseDto> getCar(@PathVariable Long id) {
        return ResponseEntity.ok().body(carService.findById(id));
    }

    @GetMapping("/get-cars")
    public ResponseEntity<List<CarResponseDto>> getAllCars() {
        return ResponseEntity.ok().body(carService.findAll());
    }

    @PutMapping("/update-car/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable Long id,
                                                    @RequestBody CarRequestDto carRequestDto) {
        return ResponseEntity.ok(carService.updateCar(id, carRequestDto));
    }

    @DeleteMapping("/delete-car/{id}")
    public ResponseEntity<CarResponseDto> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
