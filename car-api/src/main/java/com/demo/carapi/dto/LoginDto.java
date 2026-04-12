package com.demo.carapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NotBlank(message = "username boş olamaz")
    private String username;

    @NotBlank(message = "password boş olamaz")
    @Size(min = 2, message = "Şifre en az 8 karekter olmalıdır")
    private String password;


}
