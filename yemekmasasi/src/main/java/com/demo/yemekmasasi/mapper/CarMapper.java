package com.demo.yemekmasasi.mapper;

import com.demo.yemekmasasi.dto.request.CarRequestDto;
import com.demo.yemekmasasi.dto.response.CarResponseDto;
import com.demo.yemekmasasi.entity.Car;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car toEntity(CarResponseDto carResponseDto);

    CarResponseDto toDto(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarResponseDto carResponseDto, @MappingTarget Car car);

    Car toEntity(CarRequestDto carRequestDto);

    CarRequestDto toDto1(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarRequestDto carRequestDto, @MappingTarget Car car);
}