package com.userpe.service;

import com.userpe.dto.request.CarRequest;
import com.userpe.dto.response.CarResponse;
import com.userpe.entity.Car;
import com.userpe.mapper.CarMapper;
import com.userpe.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public CarService(CarMapper carMapper, CarRepository carRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    public CarResponse createCar(CarRequest carRequest){
    Car car = carMapper.toCar(carRequest);
    Car saved = carRepository.save(car);
        return carMapper.toCarResponse(saved);
    }

    public List<CarResponse> getAll() {
        List<Car> allCars =  carRepository.findAll();
        List<CarResponse> carResponses = carMapper.toCarList(allCars);
        return carResponses;
    }



}
