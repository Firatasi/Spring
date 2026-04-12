package com.demo.echoscan.service;

import com.demo.echoscan.dto.AnalysisRequest;
import com.demo.echoscan.dto.AnalysisResponse;
import com.demo.echoscan.entity.AnalysisRecord;

import java.util.Optional;

public interface AnalysisService {
    AnalysisResponse analyzeText(AnalysisRequest request);
    Optional<AnalysisRecord> findById(Long id);
}
