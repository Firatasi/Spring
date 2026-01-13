package com.firat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products",
        uniqueConstraints = @UniqueConstraint(name="uk_products_sku", columnNames="sku"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 50)
    private String sku;


    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal price;

    @Column(nullable=false)
    private Integer stock;

    @Column(nullable=false)
    private Boolean active;

    @Column(nullable=false)
    private Instant createdAt;


}
