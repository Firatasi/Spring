package com.demo.echoscan.service.impl;

import com.demo.echoscan.dto.AnalysisRequest;
import com.demo.echoscan.dto.AnalysisResponse;
import com.demo.echoscan.entity.AnalysisRecord;
import com.demo.echoscan.repository.AnalysisRecordRepository;
import com.demo.echoscan.service.AnalysisService;
import com.demo.echoscan.service.ai.AiDetectionService;
import com.demo.echoscan.service.plagiarism.PlagiarismService;
import com.demo.echoscan.service.style.StyleAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final AiDetectionService aiDetectionService;
    private final PlagiarismService plagiarismService;
    private final StyleAnalysisService styleAnalysisService;
    private final AnalysisRecordRepository analysisRecordRepository;

    public AnalysisServiceImpl (AiDetectionService aiDetectionService, PlagiarismService plagiarismService, StyleAnalysisService styleAnalysisService, AnalysisRecordRepository analysisRecordRepository) {
        this.aiDetectionService = aiDetectionService;
        this.plagiarismService = plagiarismService;
        this.styleAnalysisService = styleAnalysisService;
        this.analysisRecordRepository = analysisRecordRepository;
    }

    @Override
    public AnalysisResponse analyzeText(AnalysisRequest request) {
        String text = request.getText();

        double aiProbability = aiDetectionService.calculateAiProbability(text);
        double plagiarismScore = plagiarismService.calculatePlagiarismScore(text);
        double styleConsistencyScore = styleAnalysisService.calculateStyleConsistencyScore(text);

        String summary = buildSummary(aiProbability, plagiarismScore, styleConsistencyScore);

        AnalysisRecord record = AnalysisRecord.builder()
                .originalText(text)
                .aiProbability(aiProbability)
                .plagiarismScore(plagiarismScore)
                .styleConsistencyScore(styleConsistencyScore)
                .summary(summary)
                .createdAt(LocalDateTime.now())
                .build();
        analysisRecordRepository.save(record);

        return AnalysisResponse.builder()
                .aiProbability(aiProbability)
                .plagiarismScore(plagiarismScore)
                .styleConsistencyScore(styleConsistencyScore)
                .summary(summary)
                .build();
    }

    private String buildSummary(double ai, double plagiarism, double style) {
        return "AI probability: " + ai +
                "%, plagiarism score: " + plagiarism +
                "%, style consistency: " + style + "%.";
    }

}
