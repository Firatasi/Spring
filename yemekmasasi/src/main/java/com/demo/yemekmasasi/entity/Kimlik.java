package com.demo.yemekmasasi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//child
@Table
@Entity
public class Kimlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nim;

    @Column(nullable = false, unique = true)
    private String tcNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;




}
