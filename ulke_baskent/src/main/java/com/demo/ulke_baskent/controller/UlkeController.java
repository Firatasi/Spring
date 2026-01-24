package com.demo.ulke_baskent.controller;

import com.demo.ulke_baskent.dto.request.UlkeRequestDto;
import com.demo.ulke_baskent.dto.response.UlkeResponseDto;
import com.demo.ulke_baskent.entity.Ulke;
import com.demo.ulke_baskent.service.UlkeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/ulke")
@RestController
public class UlkeController {

    private final UlkeService ulkeService;

    public UlkeController(UlkeService ulkeService) {
        this.ulkeService = ulkeService;
    }

    @PostMapping
    public ResponseEntity<UlkeResponseDto> save(@RequestBody UlkeResponseDto ulkeResponseDto) {
        return ResponseEntity.ok(ulkeService.save(Ulke));
    }

    @GetMapping
    public ResponseEntity<List<UlkeResponseDto>> getAll() {
        return ResponseEntity.ok(ulkeService.getAll());
    }

    @PostMapping("{/id}")
    public ResponseEntity<UlkeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ulkeService.getById(id));
    }

    @PutMapping("{/id}")
    public ResponseEntity<UlkeResponseDto> update(@PathVariable Long id,
                                       @RequestBody UlkeRequestDto ulkeRequestDto) {
        return ResponseEntity.ok(ulkeService.update(id, ulkeRequestDto));
    }

}
