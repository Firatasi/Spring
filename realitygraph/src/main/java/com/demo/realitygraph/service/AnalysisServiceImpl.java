package com.demo.realitygraph.service;

import com.demo.realitygraph.dto.AnalysisRequest;
import com.demo.realitygraph.dto.AnalysisResponse;
import com.demo.realitygraph.entity.Analysis;
import com.demo.realitygraph.repository.AnalysisRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository analysisRepository;

    public AnalysisServiceImpl(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    @Override
    public AnalysisResponse createAnalysis(AnalysisRequest request) {
        Analysis analysis = Analysis.builder()
                .inputText(request.getText())
                .reliabilityScore(72)
                .biasScore(38)
                .sentiment("NEUTRAL")
                .explanation("Temporary analysis result. AI integration will be added later.")
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