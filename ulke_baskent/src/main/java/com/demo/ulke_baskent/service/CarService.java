package com.demo.ulke_baskent.service;

import com.demo.ulke_baskent.dto.request.CarRequest;
import com.demo.ulke_baskent.dto.response.CarResponse;
import com.demo.ulke_baskent.entity.Car;
import com.demo.ulke_baskent.exception.CarAlreadyExistException;
import com.demo.ulke_baskent.exception.CarNotFoundException;
import com.demo.ulke_baskent.mapper.CarMapper;
import com.demo.ulke_baskent.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarMapper carMapper;
    CarRepository carRepository;
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarResponse addCar(CarRequest carRequest) {
        Optional<Car> car = carRepository.findByModel(carRequest.getModel()); //bu modelden araç var mı
        if (car.isPresent()) {//model varsa
        throw new CarAlreadyExistException("Car already exist");
        }else  {
            Car carOne = carMapper.toCar(carRequest);
            Car savedCar = carRepository.save(carOne);
            return carMapper.toCarResponse(savedCar);
        }

    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("Car not found!"));
        return carMapper.toCarResponse(car);
    }

    public List<CarResponse> getAllCars() {
        List<Car> all = carRepository.findAll();
        return carMapper.toCarResponseList(all);

    }

    public CarResponse updateCar(Long id, CarRequest carRequest) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            Car carOne = car.get();
            carOne.setModel(carRequest.getModel());
            Car savedCar = carRepository.save(carOne);
            return carMapper.toCarResponse(savedCar);
        }else  {
            throw new CarNotFoundException("Car not found!");
        }
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("Car mmt found!"));
        carRepository.deleteById(id);
    }
}



















