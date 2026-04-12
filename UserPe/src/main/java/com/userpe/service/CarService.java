package com.userpe.service;

import com.userpe.dto.request.CarRequest;
import com.userpe.dto.response.CarResponse;
import com.userpe.entity.Car;
import com.userpe.mapper.CarMapper;
import com.userpe.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return carMapper.toCarList(allCars);
    }

   public CarResponse getCarById(Long id){
        Car car = carRepository.findById(id).orElse(null);
        if(car == null){
            return null;
        }
        return carMapper.toCarResponse(car);
   }

   public CarResponse updateCar(Long id, CarRequest carRequest){
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carOptional.get().setColor(carRequest.getColor());
            carOptional.get().setYear(carRequest.getYear());
            carOptional.get().setColor(carRequest.getColor());
            return carMapper.toCarResponse(carOptional.get());
        }
        return null;
   }

   public void deleteCar(Long id){
        Car car = carRepository.findById(id).orElse(null);
        if(car != null){
            carRepository.deleteById(id);
        }
    }

}
