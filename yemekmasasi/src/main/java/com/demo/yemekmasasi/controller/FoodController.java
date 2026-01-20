package com.demo.yemekmasasi.controller;

import com.demo.yemekmasasi.dto.request.FoodRequestDto;
import com.demo.yemekmasasi.dto.response.FoodResponseDto;
import com.demo.yemekmasasi.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/yemekmasasi")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/add-food")
    public ResponseEntity<FoodResponseDto>saveFood(@RequestBody FoodRequestDto foodRequestDto){//nesne olduğu için requestbody kullandık
        FoodResponseDto response = foodService.save(foodRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-food/{id}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable Long id){
        return ResponseEntity.ok().body(foodService.getFoodById(id));
    }

    @GetMapping("/get-foods")
    public ResponseEntity<List<FoodResponseDto>> getFoods(){
        return ResponseEntity.ok().body(foodService.getFoods());
    }

    @PutMapping("/update-food{id}")
    public ResponseEntity<FoodResponseDto> updateFood(@PathVariable Long id,
                                                      @RequestBody FoodRequestDto foodRequestDto){
        return ResponseEntity.ok(foodService.updateFood(id, foodRequestDto));
    }

    @DeleteMapping("/delete-food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id){
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

}
