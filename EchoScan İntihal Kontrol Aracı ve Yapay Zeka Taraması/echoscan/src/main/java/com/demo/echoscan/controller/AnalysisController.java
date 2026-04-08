package com.demo.echoscan.controller;

import com.demo.echoscan.dto.AnalysisRequest;
import com.demo.echoscan.dto.AnalysisResponse;
import com.demo.echoscan.service.AnalysisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin(origins = "*")// ÇOK ÖNEMLİ! Frontend uygulamanın (örneğin React 3000 portunda çalışırken) farklı bir porttaki Backend'e (8080 portu) istek atmasına izin verir. Bu olmazsa tarayıcı güvenliği (CORS) isteği engeller.
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/text")
    public AnalysisResponse analyzeText(@Valid @RequestBody AnalysisRequest request) {
        return analysisService.analyzeText(request);
    }

}
