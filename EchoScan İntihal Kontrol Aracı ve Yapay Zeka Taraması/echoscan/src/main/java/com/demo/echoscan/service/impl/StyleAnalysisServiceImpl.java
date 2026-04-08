package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.style.StyleAnalysisService;
import org.springframework.stereotype.Service;

@Service
public class StyleAnalysisServiceImpl implements StyleAnalysisService {
    @Override
    public double calculateStyleConsistencyScore(String text) {
        String[] sentences = text.split("[.!?]+");
        if (sentences.length == 0) {
            return 0.0;
        }

        double totalLength = 0;
        for (String sentence : sentences) {
            totalLength += sentence.trim().split("\\s+").length;
        }

        double average = totalLength / sentences.length;

        double variance = 0;
        for (String sentence : sentences) {
            double len = sentence.trim().split("\\s+").length;
            variance += Math.pow(len - average, 2);
        }

        variance = variance / sentences.length;

        double score = Math.max(0, 100 - variance);
        return Math.round(score * 10.0) / 10.0;
    }

}
