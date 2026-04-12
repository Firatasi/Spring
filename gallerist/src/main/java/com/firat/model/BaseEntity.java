package com.firat.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@MappedSuperclass //Her entity’ye  tek tek özellik yazmak yerine, ortak bir BaseEntity oluştururuz.Spring Data JPA’nın bu sınıfı tablo olarak oluşturmamasını sağlamak içindir.
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)
    private Date creatTime;


}
