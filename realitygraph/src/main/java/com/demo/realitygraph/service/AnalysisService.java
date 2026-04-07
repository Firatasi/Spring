package com.demo.realitygraph.service;

import com.demo.realitygraph.dto.AnalysisRequest;
import com.demo.realitygraph.dto.AnalysisResponse;

public interface  AnalysisService {
    AnalysisResponse createAnalysis(AnalysisRequest request);
    
}
