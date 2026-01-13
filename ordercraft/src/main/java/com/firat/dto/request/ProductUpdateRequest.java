package com.firat.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    @Size(max=120)
    private String name;

    @Size(max=50)
    private String sku;

    @DecimalMin("0.01")
    private BigDecimal price;

    @Min(0)
    private Integer stock;

    private Boolean active;
}
