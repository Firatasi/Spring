package com.demo.yemekmasasi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDto {
    private Long id;
    private String ad;
    private String katagori;
    private Double fiyat;
    private LocalDate eklemeTarih;
}
