package com.demo.realitygraph.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyzeText(String text) {
        String prompt = """
                Analyze the following text and return JSON in this format:
                {
                  "reliabilityScore": number,
                  "biasScore": number,
                  "sentiment": "positive/neutral/negative",
                  "explanation": "string"
                }

                TEXT:
                """ + text;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}