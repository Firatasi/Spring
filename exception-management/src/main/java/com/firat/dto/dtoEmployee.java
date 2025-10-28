package com.firat.dto;

import lombok.Data;

@Data
public class dtoEmployee {
    private Long id;
    private String name;
    private dtoDepartment department;
}
