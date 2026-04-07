package com.demo.realitygraph.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.realitygraph.dto.AnalysisRequest;
import com.demo.realitygraph.dto.AnalysisResponse;
import com.demo.realitygraph.service.AnalysisService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnalysisResponse createAnalysis(@Valid @RequestBody AnalysisRequest request) {
        return analysisService.createAnalysis(request);
    }

}
