package com.demo.yemekmasasi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String password;
    private LocalDate eklemeTarih;

    @PrePersist
    public void prePersist(){
        this.eklemeTarih = LocalDate.now();
    }

}
