package com.demo.echoscan.service.impl;

import com.demo.echoscan.service.ai.AiDetectionService;
import org.springframework.stereotype.Service;

@Service
public class AiDetectionServiceImpl implements AiDetectionService {
    @Override
    public double calculateAiProbability(String text) {
       int wordCount = text.trim().split("\\s+").length;

       if (wordCount < 100) {
           return 35.0;
       }else if (wordCount < 300) {
           return 48.0;
       }else {
           return 57.0;
       }

    }
}
