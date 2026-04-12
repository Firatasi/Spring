package com.demo.carapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Serializable {
    private String name;
    private String lastName;
    private String password;
    private LocalDate eklemeTarih;
}
