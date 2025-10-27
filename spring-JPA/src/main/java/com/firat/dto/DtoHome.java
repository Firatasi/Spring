package com.firat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoHome {

    private Long id;
    private BigDecimal price;
    private List<DtoRoom> rooms = new ArrayList<>();

}
