package com.demo.ulke_baskent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Parent
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Baskent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "baskent", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ulke ulke;
}
