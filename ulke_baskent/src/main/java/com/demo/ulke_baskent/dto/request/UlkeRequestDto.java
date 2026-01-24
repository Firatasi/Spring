package com.demo.ulke_baskent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UlkeRequestDto {


    private String name;

    private Integer nufus;

    private Integer baskent_Id;
}
