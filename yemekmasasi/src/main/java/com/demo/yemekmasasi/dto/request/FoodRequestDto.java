package com.demo.yemekmasasi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {
    private String ad;
    private String katagori;
    private Double fiyat;
}
