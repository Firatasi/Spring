package com.userpe.mapper;
import com.userpe.dto.request.CarRequest;
import com.userpe.dto.response.CarResponse;
import com.userpe.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toCar(CarRequest  dto);
    CarResponse toCarResponse(Car car);
    List<CarResponse> toCarList(List<Car> cars);
}
