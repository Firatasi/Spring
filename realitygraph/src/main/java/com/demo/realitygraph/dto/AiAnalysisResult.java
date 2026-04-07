package com.demo.realitygraph.dto;

import lombok.Data;

@Data
public class AiAnalysisResult {

    private Integer reliabilityScore;
    private Integer biasScore;
    private String sentiment;
    private String explanation;
}
