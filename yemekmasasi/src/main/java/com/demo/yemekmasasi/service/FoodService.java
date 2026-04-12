package com.demo.yemekmasasi.service;

import com.demo.yemekmasasi.dto.request.FoodRequestDto;
import com.demo.yemekmasasi.dto.response.FoodResponseDto;
import com.demo.yemekmasasi.entity.Food;
import com.demo.yemekmasasi.mapper.FoodMapper;
import com.demo.yemekmasasi.repository.FoodRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }


    public FoodResponseDto save(FoodRequestDto foodRequestDto) {
        Food food = foodMapper.toFood(foodRequestDto);
        Food savedFood = foodRepository.save(food);

        return foodMapper.toFoodResponseDto(savedFood);
    }

    public FoodResponseDto getFoodById(Long id) {
        Optional<Food> byId = foodRepository.findById(id);
        if (byId.isPresent()) {
            return foodMapper.toFoodResponseDto(byId.get());
        }
        return null;
    }


    public List<FoodResponseDto> getFoods() {
        List<Food> foods = foodRepository.findAll();
        return foodMapper.toFoodResponseDto(foods);
    }


    public FoodResponseDto updateFood(Long id, FoodRequestDto foodRequestDto) {
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Food not found"));

        Food updated =  foodMapper.toFood(foodRequestDto);
        Food savedFood = foodRepository.save(updated);
        return foodMapper.toFoodResponseDto(savedFood);

    }


    public void deleteFood(Long id) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found: " + id));

        foodRepository.delete(food);
    }
}
