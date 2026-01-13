package com.firat.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;
    private Instant createdAt;
}
