package com.demo.echoscan.controller;

import com.demo.echoscan.dto.AnalysisRequest;
import com.demo.echoscan.dto.AnalysisResponse;
import com.demo.echoscan.entity.AnalysisRecord;
import com.demo.echoscan.service.AnalysisService;
import com.demo.echoscan.service.FileParsingService;
import com.demo.echoscan.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin(origins = "*")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final FileParsingService fileParsingService;
    private final ReportService reportService;

    public AnalysisController(AnalysisService analysisService, FileParsingService fileParsingService, ReportService reportService) {
        this.analysisService = analysisService;
        this.fileParsingService = fileParsingService;
        this.reportService = reportService;
    }

    @PostMapping("/text")
    public AnalysisResponse analyzeText(@Valid @RequestBody AnalysisRequest request) {
        return analysisService.analyzeText(request);
    }

    @PostMapping("/file")
    public AnalysisResponse analyzeFile(@RequestParam("file") MultipartFile file) throws IOException {
        String extractedText = fileParsingService.extractText(file);
        
        if (extractedText == null || extractedText.trim().length() < 50) {
            throw new IllegalArgumentException("Dosya içeriği çok kisa veya metin ayristirilamadi (Min. 50 karakter gerekli).");
        }

        AnalysisRequest request = AnalysisRequest.builder().text(extractedText).build();
        return analysisService.analyzeText(request);
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Long id) {
        AnalysisRecord record = analysisService.findById(id)
                .orElseThrow(() -> new RuntimeException("Analiz kaydi bulunamadi: " + id));

        byte[] pdfContent = reportService.generatePdfReport(record);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EchoScan_Rapor_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}
