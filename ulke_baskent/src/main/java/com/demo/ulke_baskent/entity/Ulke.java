package com.demo.ulke_baskent.entity;

import jakarta.persistence.*;
import lombok.*;

//child
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ulke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer nufus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "baskent_id")
    private Baskent baskent;


}
