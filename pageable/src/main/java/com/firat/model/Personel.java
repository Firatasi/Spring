package com.firat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;

    @ManyToOne
    private Department department;
}
