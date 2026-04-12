package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.plagiarism.PlagiarismService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlagiarismServiceImpl implements PlagiarismService {

    private final RestTemplate restTemplate;

    @Value("${AI_SERVICE_URL:http://localhost:8000}")
    private String AI_SERVICE_URL;

    public PlagiarismServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double calculatePlagiarismScore(String text, Long recordId) {
        try {
            // 1. Store and embed the text in the Python AI Service
            Map<String, Object> storeRequest = new HashMap<>();
            storeRequest.put("analysisRecordId", recordId);
            storeRequest.put("text", text);
            restTemplate.postForObject(AI_SERVICE_URL + "/analyze-and-store", storeRequest, Map.class);

            // 2. Perform a similarity search
            // We search for chunks most similar to this text, excluding the ones we just added by using the recordId
            Map<String, Object> searchRequest = new HashMap<>();
            searchRequest.put("queryText", text);
            searchRequest.put("exclude_id", recordId);
            searchRequest.put("limit", 5);

            Map<String, Object> response = restTemplate.postForObject(AI_SERVICE_URL + "/similarity/search", searchRequest, Map.class);
            
            if (response != null && response.containsKey("results")) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
                if (results.isEmpty()) return 0.0;

                // Max similarity found in the database
                double maxSimilarity = results.stream()
                        .mapToDouble(r -> ((Number) r.get("similarity")).doubleValue())
                        .max()
                        .orElse(0.0);

                // Convert to percentage
                return Math.round(maxSimilarity * 100.0 * 10.0) / 10.0;
            }

        } catch (Exception e) {
            System.err.println("Error calling AI Service: " + e.getMessage());
        }

        return 0.0;
    }
}
