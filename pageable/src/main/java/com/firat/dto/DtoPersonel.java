package com.firat.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DtoPersonel {
    private Long  id;

    private String firstname;

    private String lastname;

    private DtoDepartment department;

}
