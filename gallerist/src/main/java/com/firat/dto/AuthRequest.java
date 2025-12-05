package com.firat.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class AuthRequest  {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
