package com.firat.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity  //!bunu bulması için startera ekle!!JPA (Java Persistence API) tarafından bir sınıfın veritabanı tablosu olduğunu belirtmek için kullanılır.
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_of_day", nullable = true)
    private String birthOfDate;

    @ManyToMany
    //@JoinTable(name = {"student", JoinColumns = @JoinColumn(name ="student_id"), inverseJoinColumns = @JoinColumn(name ="course_id"))
    private List<Course> courses;
}
