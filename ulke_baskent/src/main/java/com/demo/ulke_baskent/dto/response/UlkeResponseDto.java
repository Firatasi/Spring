package com.demo.ulke_baskent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UlkeResponseDto {

    private Long id;

    private String name;

    private Integer nufus;

    private Integer baskent_Id;

    private String baskent_Name;
}
