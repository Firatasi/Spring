package com.demo.echoscan.service.plagiarism;

public interface PlagiarismService {
    double calculatePlagiarismScore(String text, Long recordId);
}
