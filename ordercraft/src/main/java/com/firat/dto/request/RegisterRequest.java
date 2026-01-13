package com.firat.dto.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotBlank @Size(min=3, max=60)
    private String username;

    @NotBlank @Size(min=4, max=100)
    private String password;
}
