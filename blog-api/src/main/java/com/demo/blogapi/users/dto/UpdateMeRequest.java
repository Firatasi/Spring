package com.demo.blogapi.users.dto;
import jakarta.validation.constraints.Size;

public record UpdateMeRequest(
        @Size(max = 120) String fullName,
        @Size(min = 6, max = 72) String password
) {};

