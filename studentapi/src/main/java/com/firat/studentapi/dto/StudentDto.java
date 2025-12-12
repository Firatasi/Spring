package com.firat.studentapi.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private Double grade;
}
