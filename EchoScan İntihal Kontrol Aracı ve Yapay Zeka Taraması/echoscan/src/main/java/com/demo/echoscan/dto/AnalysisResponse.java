package com.demo.echoscan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalysisResponse {

    private Double aiProbability;
    private Double plagiarismScore;
    private Double styleConsistencyScore;
    private String summary;

}


