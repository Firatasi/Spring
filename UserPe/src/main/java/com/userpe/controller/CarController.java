package com.userpe.controller;

import com.userpe.dto.request.CarRequest;
import com.userpe.dto.response.CarResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car/v1")
public class CarController {

    @GetMapping("/get-all")
    public List<CarResponse> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Optional<CarResponse> findById(@PathVariable Long id) {
        return null;
    }

    @PutMapping
    public CarResponse update(@RequestBody CarRequest carRequest) {
        return null;
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {

    }

}
