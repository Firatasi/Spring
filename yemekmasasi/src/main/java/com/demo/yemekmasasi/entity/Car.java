package com.demo.yemekmasasi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "car", schema = "yemekmasasi")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(nullable = false, length = 50)
    private String marka;

    @Size(max = 50)
    @NotNull
    @Column(nullable = false, length = 50)
    private String model;

    private Integer yil;

    @Size(max = 30)
    @Column(length = 30)
    private String renk;

    @Column(precision = 10, scale = 2)
    private BigDecimal fiat;


    private Boolean otomatik;


    private Instant eklenmteTarihi;


}