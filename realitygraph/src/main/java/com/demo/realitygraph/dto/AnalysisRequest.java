package com.demo.realitygraph.dto;

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
public class AnalysisRequest {
    @NotBlank(message="Text Not Blank")
    @Size(min = 10, max = 5000, message = "Text must be between 10 and 5000 characters")
    private String text;

}
