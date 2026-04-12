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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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
    @Transactional
    public AnalysisResponse analyzeText(AnalysisRequest request) {
        String text = request.getText();

        AnalysisRecord record = AnalysisRecord.builder()
                .originalText(text)
                .aiProbability(0.0)
                .plagiarismScore(0.0)
                .styleConsistencyScore(0.0)
                .createdAt(LocalDateTime.now())
                .build();
        record = analysisRecordRepository.save(record);

        double aiProbability = aiDetectionService.calculateAiProbability(text);
        double plagiarismScore = plagiarismService.calculatePlagiarismScore(text, record.getId());
        double styleConsistencyScore = styleAnalysisService.calculateStyleConsistencyScore(text);

        String summary = buildSummary(aiProbability, plagiarismScore, styleConsistencyScore);

        record.setAiProbability(aiProbability);
        record.setPlagiarismScore(plagiarismScore);
        record.setStyleConsistencyScore(styleConsistencyScore);
        record.setSummary(summary);
        analysisRecordRepository.save(record);

        return AnalysisResponse.builder()
                .id(record.getId())
                .aiProbability(aiProbability)
                .plagiarismScore(plagiarismScore)
                .styleConsistencyScore(styleConsistencyScore)
                .summary(summary)
                .build();
    }

    @Override
    public Optional<AnalysisRecord> findById(Long id) {
        return analysisRecordRepository.findById(id);
    }

    private String buildSummary(double ai, double plagiarism, double style) {
        // CLEAN INDUSTRY TERMINOLOGY - No decimals, no trailing dots
        return "Yapay Zeka Tespiti: %" + (int)Math.floor(ai) +
                ", İntihal Oranı: %" + (int)Math.floor(plagiarism) +
                ", Benzerlik Endeksi: %" + (int)Math.floor(style);
    }
}
