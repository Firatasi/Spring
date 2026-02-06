package com.demo.ulke_baskent.mapper;

import com.demo.ulke_baskent.dto.request.CarRequest;
import com.demo.ulke_baskent.dto.response.CarResponse;
import com.demo.ulke_baskent.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toCar(CarRequest request);
    CarResponse toCarResponse(Car car);
    List<CarResponse> toCarResponseList(List<Car> cars);
}
