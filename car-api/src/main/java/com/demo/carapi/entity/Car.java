package com.demo.carapi.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marka;

    private String model;

    private Integer yil;

    private String renk;

    private BigDecimal fiat;


    private Boolean otomatik;


    private Instant eklenmteTarihi;
    @PrePersist
    public void prePersist() {
        if (eklenmteTarihi == null) {
            eklenmteTarihi = Instant.now(); //eklenme tarihi otomatik olarak gelir
        }
    }

}