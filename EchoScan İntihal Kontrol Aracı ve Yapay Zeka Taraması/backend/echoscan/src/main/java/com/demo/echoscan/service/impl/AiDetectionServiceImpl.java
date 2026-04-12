package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.ai.AiDetectionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AiDetectionServiceImpl implements AiDetectionService {

    @Override
    public double calculateAiProbability(String text) {
        if (text == null || text.trim().isEmpty()) return 0.0;

        String cleanText = text.trim();
        String[] sentences = cleanText.split("[.!?]+");
        String[] words = cleanText.toLowerCase().replaceAll("[^a-zA-Zçğışöü\s]", "").split("\s+");

        if (words.length < 10) return 15.0; // Too short

        // 1. ADVANCED BURSTINESS SCAN (Weight: 40%)
        // AI text is monotonic. Humans use varied sentence lengths.
        double burstiness = calculateAdvancedBurstiness(sentences);

        // 2. VOCABULARY DIVERSITY (Weight: 20%)
        // AI has limited "Randomness" in word choice (Type-Token Ratio).
        double ttr = calculateTTR(words);

        // 3. LEXICAL DENSITY & FUNCTIONAL ANALYSIS (Weight: 25%)
        // AI overuses specific functional structures and neutral tokens.
        double lexicalDensity = calculateAdvancedLexicalDensity(words);

        // 4. NEURAL PATTERN CONSISTENCY (Weight: 15%)
        // Checking for repetitive patterns and high-probability sequences.
        double neuralConsistency = calculateNeuralConsistency(cleanText);

        // BALANCED SCORING ENGINE
        double aiScore = 45.0; // Baseline

        // Burstiness Logic (The "State-of-the-Art" differentiator)
        if (burstiness < 4.5) aiScore += 35;      // Critically robotic
        else if (burstiness < 8.0) aiScore += 20;  // Highly stable
        else if (burstiness > 18.0) aiScore -= 20; // High human variance
        else if (burstiness > 25.0) aiScore -= 30; // Pure human chaotic flow

        // TTR Logic
        if (ttr < 0.40) aiScore += 12;
        else if (ttr > 0.70) aiScore -= 12;

        // Lexical Density Logic
        if (lexicalDensity > 0.60) aiScore += 18;
        else if (lexicalDensity < 0.35) aiScore -= 8;

        // Neural Consistency
        if (neuralConsistency > 0.80) aiScore += 15;

        // Final result bounded by 0 and 99 (never 100% to allow for error)
        return Math.max(2.0, Math.min(99.0, aiScore));
    }

    private double calculateAdvancedBurstiness(String[] sentences) {
        if (sentences.length < 2) return 5.0;
        List<Integer> lengths = new ArrayList<>();
        for (String s : sentences) {
            int len = s.trim().split("\s+").length;
            if (len > 2) lengths.add(len);
        }
        if (lengths.size() < 2) return 5.0;

        double mean = lengths.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double stdev = Math.sqrt(lengths.stream()
                .mapToDouble(l -> Math.pow(l - mean, 2))
                .average().orElse(0.0));

        // State-of-the-art burstiness is stdev relative to mean
        return mean > 0 ? (stdev / mean) * 10 : stdev;
    }

    private double calculateTTR(String[] words) {
        if (words.length == 0) return 0.0;
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return (double) uniqueWords.size() / words.length;
    }

    private double calculateAdvancedLexicalDensity(String[] words) {
        // Robust Cross-Language Stopword Analysis
        Set<String> stopwords = new HashSet<>(Arrays.asList(
            // English Top 20
            "the", "be", "to", "of", "and", "a", "in", "that", "have", "it", "for", "not", "on", "with", "he", "as", "you", "do", "at", "this",
            // Turkish Top 20
            "bir", "ve", "bu", "da", "de", "için", "ne", "o", "kadar", "olan", "gibi", "ama", "en", "daha", "her", "ise", "ki", "sonra", "ancak", "yok"
        ));
        long count = Arrays.stream(words).filter(stopwords::contains).count();
        return (double) count / words.length;
    }

    private double calculateNeuralConsistency(String text) {
        // Simple heuristic for repetitive structures like "In conclusion", "Additionally", "Overall"
        Set<String> markers = new HashSet<>(Arrays.asList(
            "sonuç olarak", "özetle", "ayrıca", "bu bağlamda", "dahası",
            "in conclusion", "furthermore", "moreover", "overall", "specifically"
        ));
        String lowerTrimmed = text.toLowerCase();
        long hits = markers.stream().filter(lowerTrimmed::contains).count();
        return hits > 2 ? 0.9 : 0.4;
    }
}
