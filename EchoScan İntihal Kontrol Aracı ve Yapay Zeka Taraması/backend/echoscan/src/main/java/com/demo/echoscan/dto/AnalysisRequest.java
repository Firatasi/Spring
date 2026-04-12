package com.demo.echoscan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalysisRequest {
    @NotBlank(message = "Text cannot be empty")
    @Size(min = 50, message = "Text must be at least 50 characters")
    private String text;
}
