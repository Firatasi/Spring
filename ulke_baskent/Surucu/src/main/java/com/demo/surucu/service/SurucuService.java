package com.demo.surucu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SurucuService {

    private final RestTemplate restTemplate;

    public SurucuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



}
