package com.demo.carapi.mapper;

import com.demo.carapi.dto.request.CarRequestDto;
import com.demo.carapi.dto.response.CarResponseDto;
import com.demo.carapi.entity.Car;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarResponseDto toDto(Car car);
    Car toEntity(CarRequestDto carRequestDto);
    List<CarResponseDto> toDtoList(List<Car> cars);

}