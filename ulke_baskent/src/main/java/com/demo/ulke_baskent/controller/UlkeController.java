package com.demo.ulke_baskent.controller;

import com.demo.ulke_baskent.dto.request.UlkeRequestDto;
import com.demo.ulke_baskent.dto.response.UlkeResponseDto;
import com.demo.ulke_baskent.service.UlkeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("api/v1/ulke")
@RestController
public class UlkeController {

    private final UlkeService ulkeService;

    public UlkeController(UlkeService ulkeService) {
        this.ulkeService = ulkeService;
    }

    @PostMapping
    public ResponseEntity<UlkeResponseDto> save(@Valid @RequestBody UlkeRequestDto ulkeRequestDto) {
        UlkeResponseDto ulkeResponseDto = ulkeService.save(ulkeRequestDto);
        return ResponseEntity.ok(ulkeResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UlkeResponseDto>> getAll() {
        UlkeResponseDto ulkeResponseDto = (UlkeResponseDto) ulkeService.getAllUlke();
        return ResponseEntity.ok().body(Collections.singletonList(ulkeResponseDto));
    }

    @PostMapping("{/id}")
    public ResponseEntity<UlkeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ulkeService.getById(id));
    }

    @PutMapping("{/id}")
    public ResponseEntity<UlkeResponseDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody UlkeRequestDto ulkeRequestDto) {
        return ResponseEntity.ok(ulkeService.update(id, ulkeRequestDto));
    }
    @DeleteMapping
    public ResponseEntity<UlkeResponseDto> delete(@PathVariable Long id) {
        ulkeService.deleteUlke(id);
        return ResponseEntity.ok().build();
    }

}
