package com.demo.yemekmasasi.mapper;

import com.demo.yemekmasasi.dto.request.FoodRequestDto;
import com.demo.yemekmasasi.dto.response.FoodResponseDto;
import com.demo.yemekmasasi.entity.Food;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    Food toFood(FoodRequestDto foodRequestDto); //requestdtoyu  yemeğe çevirir
    FoodResponseDto toFoodResponseDto(Food food); //yemeği response dtoa çevirir
    List<FoodResponseDto>  toFoodResponseDto(List<Food> foodList);//tüm kullanıcıları döner

}
