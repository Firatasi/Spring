package com.userpe.dto.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String model;

    private String color;

    private int year;

    private LocalDateTime date;
}
