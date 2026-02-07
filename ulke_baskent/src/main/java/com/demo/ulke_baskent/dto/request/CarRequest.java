package com.demo.ulke_baskent.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    @Size(min = 1, max = 50)
    @NotBlank(message = "marka boş olamaz")
    private String marka;

    @NotBlank(message = "model boş olamaz")
    private String model;

    @Min(value = 100, message = "100den az olamaz")
    @NotBlank(message = "fiyat boş olamaz")
    private BigDecimal fiyat;

    @Positive
    @NotBlank(message = "yaş boş olamaz")
    private Short yas;
}
