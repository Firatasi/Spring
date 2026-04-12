package com.firat.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//otomatik olarak id versin diye
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne//ilişkinin sahibi ilk onetoone yazılan yer
    private Adress adress;

}
