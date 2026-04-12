package com.firat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDepartment {
    private Long id;
    private String firstName;
    private String lastName;
    public DtoDepartment department;
}
