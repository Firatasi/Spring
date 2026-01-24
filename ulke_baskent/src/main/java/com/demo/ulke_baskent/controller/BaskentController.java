package com.demo.ulke_baskent.controller;

import com.demo.ulke_baskent.dto.request.BaskentRequestDto;
import com.demo.ulke_baskent.dto.response.BaskentResponseDto;
import com.demo.ulke_baskent.service.BaskentService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/baskent")
@RestController
public class BaskentController {

    private final BaskentService baskentService;

    public BaskentController(BaskentService baskentService) {
        this.baskentService = baskentService;
    }

    @PostMapping
    public ResponseEntity<BaskentResponseDto> saveBaskent(@RequestBody BaskentRequestDto baskentRequestDto) {
        BaskentResponseDto baskentResponseDto = baskentService.save(baskentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(baskentResponseDto);//eklerken status donmek daha mantıklı
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaskentResponseDto> getBaskentById(@PathVariable Long id) {
        BaskentResponseDto dto = baskentService.getBaskentById(id);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public ResponseEntity<List<BaskentResponseDto>> getAllBaskent() {
        List<BaskentResponseDto> allBaskent = baskentService.getAllBaskent();
        return ResponseEntity.ok(allBaskent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaskentResponseDto> updateBaskent(@PathVariable Long id,
                                                            @RequestBody BaskentRequestDto baskentRequestDto) {
        BaskentResponseDto dto = baskentService.updateBaskent(id, baskentRequestDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaskent(@PathVariable Long id) {
        baskentService.deleteBaskent(id);
        return ResponseEntity.noContent().build();
    }







}
