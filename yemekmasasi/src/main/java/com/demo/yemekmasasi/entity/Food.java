package com.demo.yemekmasasi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "yemek")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    private String katagori;

    private Double fiyat;

    private LocalDate eklemeTarih;

    @PrePersist
    public void prePersist(){
        this.eklemeTarih = LocalDate.now();
    }

}
