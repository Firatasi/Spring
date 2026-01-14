package com.firat.mapper;

import com.firat.dto.request.ProductCreateRequest;
import com.firat.dto.response.ProductResponse;
import com.firat.entity.Product;

import java.time.Instant;

public class ProductMapper {
    private ProductMapper() {}

    public static Product toEntity(ProductCreateRequest req) {
        return Product.builder()
                .name(req.getName())
                .sku(req.getSku())
                .price(req.getPrice())
                .stock(req.getStock())
                .active(req.getActive())
                .createdAt(Instant.now())
                .build();
    }

    public static ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .sku(p.getSku())
                .price(p.getPrice())
                .stock(p.getStock())
                .active(p.getActive())
                .createdAt(p.getCreatedAt())
                .build();
    }

}
