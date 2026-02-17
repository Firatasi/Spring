package com.demo.surucu.controller;

import com.demo.surucu.CarResponse;
import com.demo.surucu.service.SurucuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVa riable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/v1/surucu")
@RestController
public class SurucuController {

    private SurucuService surucuService;

    public SurucuController(SurucuService surucuService) {
        this.surucuService = surucuService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id){
        return ResponseEntity.ok(surucuService.getCar(id));
    }
}
