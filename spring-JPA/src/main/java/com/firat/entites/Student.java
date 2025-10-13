package com.firat.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  //JPA (Java Persistence API) tarafından bir sınıfın veritabanı tablosu olduğunu belirtmek için kullanılır.
@Table(name = "student")
@Getter
@Setter //(getter setter yerine data da kullanilabilir sadece @Data)
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id'nin her yeni kayıtta birer birer artmasını sağlar
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 80) //nullable false boş bırakılamaz
    private String firstName;

    @Column(name = "last_name", nullable = false,length = 80)
    private String lastName;

    @Column(name = "birth_of_day", nullable = true)
    private String birthOfDate;
}
