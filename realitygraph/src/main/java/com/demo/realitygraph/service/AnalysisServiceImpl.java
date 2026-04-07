package com.demo.realitygraph.service;

import com.demo.realitygraph.dto.AiAnalysisResult;
import com.demo.realitygraph.dto.AnalysisRequest;
import com.demo.realitygraph.dto.AnalysisResponse;
import com.demo.realitygraph.entity.Analysis;
import com.demo.realitygraph.repository.AnalysisRepository;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final AnalysisRepository analysisRepository;
    private final GeminiService geminiService;

    public AnalysisServiceImpl(AnalysisRepository analysisRepository, GeminiService geminiService) {
        this.analysisRepository = analysisRepository;
        this.geminiService = geminiService;
    }

    @Override
    public AnalysisResponse createAnalysis(AnalysisRequest request) {
        String aiResult = geminiService.analyzeText(request.getText());

        AiAnalysisResult parsed;

        try {
            parsed = objectMapper.readValue(aiResult, AiAnalysisResult.class);
        } catch (Exception e) {
            throw new RuntimeException("AI response parsing failed");
        }

        Analysis analysis = Analysis.builder()
                .inputText(request.getText())
                .reliabilityScore(parsed.getReliabilityScore())
                .biasScore(parsed.getBiasScore())
                .sentiment(parsed.getSentiment())
                .explanation(parsed.getExplanation())
                .build();

        Analysis savedAnalysis = analysisRepository.save(analysis);

        return new AnalysisResponse(
                savedAnalysis.getId(),
                savedAnalysis.getInputText(),
                savedAnalysis.getReliabilityScore(),
                savedAnalysis.getBiasScore(),
                savedAnalysis.getSentiment(),
                savedAnalysis.getExplanation(),
                savedAnalysis.getCreatedAt()
        );
    }
}