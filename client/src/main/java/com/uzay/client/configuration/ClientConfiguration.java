package com.uzay.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .baseUrl("http://localhost:8080/api/game")
                .build();
    }
}
