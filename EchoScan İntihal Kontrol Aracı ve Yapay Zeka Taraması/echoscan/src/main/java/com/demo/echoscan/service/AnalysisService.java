package com.demo.echoscan.service;

import com.demo.echoscan.dto.AnalysisRequest;
import com.demo.echoscan.dto.AnalysisResponse;

public interface AnalysisService {
    AnalysisResponse analyzeText(AnalysisRequest request);
}
