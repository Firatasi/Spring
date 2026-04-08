package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.plagiarism.PlagiarismService;
import org.springframework.stereotype.Service;

@Service
public class PlagiarismServiceImpl implements PlagiarismService {


    @Override
    public double calculatePlagiarismScore(String text) {

        String lower = text.toLowerCase();

        int repeatedCount = 0;
        String[] words = lower.split("\\s+");

        for(int i = 1; i < words.length; i++) {
            if (words[i].equals(words[i-1])) {
                repeatedCount++;
            }
        }

        double score = Math.min(100.0, repeatedCount * 3.5);
        return Math.round(score * 10.0) / 10.0;
    }
}
