package com.demo.carapi.service;

import com.demo.carapi.dto.request.CarRequestDto;
import com.demo.carapi.dto.response.CarResponseDto;
import com.demo.carapi.entity.Car;
import com.demo.carapi.mapper.CarMapper;
import com.demo.carapi.repository.CarRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@PreAuthorize parametrelere erişebiliyor
//@PostAuthorize return değerine erişebiliyor ikiside method level security kodu

@Data
@Service
public class CarService {

    private final CarRespository carRespository;
    private final CarMapper carMapper;

    public CarService(CarRespository carRespository, CarMapper carMapper) {
        this.carRespository = carRespository;
        this.carMapper = carMapper;
    }

    public CarResponseDto save(CarRequestDto carRequestDto) {
        Car car = carMapper.toEntity(carRequestDto);
        Car carSaved = carRespository.save(car);
        return carMapper.toDto(carSaved);
    }

    public CarResponseDto findById(Long id) {
        Optional<Car> car = carRespository.findById(id);
        if (car.isPresent()) {
            return carMapper.toDto(car.get());
        }
        return null;
    }

    public List<CarResponseDto> findAll() {
        List<Car> cars = carRespository.findAll();
        return carMapper.toDtoList(cars);
    }


    public CarResponseDto updateCar(Long id, CarRequestDto carRequestDto) {
        Car car = carRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        car.setMarka(carRequestDto.getMarka());
        car.setModel(carRequestDto.getModel());
        car.setYil(carRequestDto.getYil());
        car.setRenk(carRequestDto.getRenk());
        car.setFiat(carRequestDto.getFiat());
        car.setOtomatik(carRequestDto.getOtomatik());


        Car saved = carRespository.save(car);
        return carMapper.toDto(saved);

    }

    public void deleteById(Long id) {
        Car car = carRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        carRespository.deleteById(id);
    }

}
