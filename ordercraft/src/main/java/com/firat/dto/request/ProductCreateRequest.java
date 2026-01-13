package com.firat.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateRequest {
    @NotBlank
    @Size(max=120)
    private String name;

    @NotBlank
    @Size(max=50)
    private String sku;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Boolean active;
}
