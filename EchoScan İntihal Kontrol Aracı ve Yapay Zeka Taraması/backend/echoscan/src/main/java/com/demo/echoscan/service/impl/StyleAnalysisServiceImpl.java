package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.style.StyleAnalysisService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StyleAnalysisServiceImpl implements StyleAnalysisService {

    @Override
    public double calculateStyleConsistencyScore(String text) {
        if (text == null || text.trim().isEmpty()) return 100.0;

        String[] sentences = text.split("[.!?]+");
        if (sentences.length < 3) return 80.0;

        // 1. Structural Similarity (Starts of sentences)
        double structuralConsistency = checkStructuralSimilarity(sentences);

        // 2. Average Word Length Consistency
        double vocabConsistency = checkVocabConsistency(sentences);

        // Score logic: Higher consistency is "bad" (more mechanical/AI-like) in some contexts,
        // but "good" if the style is uniform. We'll return a score of how "Consistent" the style is.
        double score = (structuralConsistency + vocabConsistency) / 2.0;

        return Math.round(score * 10.0) / 10.0;
    }

    private double checkStructuralSimilarity(String[] sentences) {
        // How many sentences start with the same word/part of speech?
        Map<String, Integer> starters = new HashMap<>();
        for (String s : sentences) {
            String[] words = s.trim().split("\\s+");
            if (words.length > 0) {
                String firstWord = words[0].toLowerCase();
                starters.put(firstWord, starters.getOrDefault(firstWord, 0) + 1);
            }
        }
        
        // Ratio of repeated starters
        double repetitiveness = (double) starters.values().stream().filter(v -> v > 1).mapToInt(Integer::intValue).sum() / sentences.length;
        return 100.0 * (1.0 - repetitiveness); // Higher score = more varied/natural structure
    }

    private double checkVocabConsistency(String[] sentences) {
        List<Double> avgWordLengths = new ArrayList<>();
        for (String s : sentences) {
            String[] words = s.trim().split("\\s+");
            if (words.length > 0) {
                double avgLen = Arrays.stream(words).mapToInt(String::length).average().orElse(0.0);
                avgWordLengths.add(avgLen);
            }
        }

        double overallMean = avgWordLengths.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variance = avgWordLengths.stream().mapToDouble(l -> Math.pow(l - overallMean, 2)).average().orElse(0.0);
        
        // High variance = rich style. Low variance = mechanical consistency.
        // We'll return a "Consistency Score" where high = very consistent.
        if (variance < 0.5) return 95.0;
        if (variance > 2.0) return 60.0;
        return 80.0;
    }
}
