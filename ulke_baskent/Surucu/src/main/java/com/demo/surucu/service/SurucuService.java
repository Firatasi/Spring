package com.demo.surucu.service;

import com.demo.surucu.CarResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class SurucuService {

    private final RestTemplate restTemplate;

    public SurucuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CarResponse getCar(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8080//api/car/v1/{id}", CarResponse.class, id);
    }


}
