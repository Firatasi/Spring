package com.firat.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//otomatik id atasın diye
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "adress") //ilk işaretlenen yer değil ilişkinin sahibi ilk onetone yazılan yer mappedby kullanılmalı
    private Customer customer;//çift  taraflı ilişkilerde mappedby zorunlu


}
