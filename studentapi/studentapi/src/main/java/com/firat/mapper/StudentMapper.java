package com.firat.mapper;

import com.firat.dto.request.StudentRequestCreate;
import com.firat.dto.response.StudentResponse;
import com.firat.entity.Student;


import java.time.Instant;

public class StudentMapper {
    public static Student toEntity(StudentRequestCreate studentRequestCreate) {
        return Student.builder()
                .firstName(studentRequestCreate.getFirstName())
                .lastName(studentRequestCreate.getLastName())
                .email(studentRequestCreate.getEmail())
                .createdAt(Instant.now())
                .build();
    }

    public static StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .createdAt(student.getCreatedAt())
                .build();
    }
}
