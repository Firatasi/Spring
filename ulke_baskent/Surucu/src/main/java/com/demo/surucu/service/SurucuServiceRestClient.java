package com.demo.surucu.service;

import com.demo.surucu.CarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class SurucuServiceRestClient {

    private final RestClient restClient;
    public SurucuServiceRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<CarResponse> getCar(Long id) {
        return null;
    }



}
