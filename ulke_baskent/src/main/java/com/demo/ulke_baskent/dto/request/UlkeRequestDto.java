package com.demo.ulke_baskent.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UlkeRequestDto {

    @NotBlank(message = "İsim kısmı boş bırakılamaz! ")
    private String name;

    @Min(value = 1, message = "nüfus 1 den büyük olmalı")
    private Integer nufus;

    @Max(value = 10000, message = "basken_id 10000 den büyük olamaz")
    private Integer baskent_Id;
}
