package com.demo.realitygraph.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalysisResponse {

    private Long id;
    private String inputText;
    private Integer reliabilityScore;
    private Integer biasScore;
    private String sentiment;
    private String explanation;
    private LocalDateTime createdAt;


}
