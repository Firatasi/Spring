package com.demo.ulke_baskent.service;

import com.demo.ulke_baskent.dto.request.UlkeRequestDto;
import com.demo.ulke_baskent.dto.response.UlkeResponseDto;
import com.demo.ulke_baskent.entity.Baskent;
import com.demo.ulke_baskent.entity.Ulke;
import com.demo.ulke_baskent.mapper.BaskentMapper;
import com.demo.ulke_baskent.mapper.UlkeMapper;
import com.demo.ulke_baskent.repository.UlkeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data

@Service
public class UlkeService {

    private final UlkeRepository ulkeRepository;
    private final UlkeMapper ulkeMapper;
    private final BaskentMapper baskentMapper;

    public UlkeService(UlkeRepository ulkeRepository, UlkeMapper ulkeMapper,
                       BaskentMapper baskentMapper) {
        this.ulkeRepository = ulkeRepository;
        this.ulkeMapper = ulkeMapper;
        this.baskentMapper = baskentMapper;
    }

    public List<UlkeResponseDto> getAllUlke() {
    List<Baskent> ulkeList = ulkeRepository.findAll();
    return ulkeMapper.toUlkeResponseDtoList(ulkeList);
    }

    public UlkeResponseDto getById(Long id) {
        Ulke ulke= ulkeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ulke bulunamadı!")).getUlke();
        if (ulke == null) {
            return null;
        }
        return ulkeMapper.toUlkeResponseDto(ulke);
    }


    public UlkeResponseDto update(Long id, UlkeRequestDto ulkeRequestDto) {
        Optional<Ulke> ulkeOptional = ulkeRepository.findById(id).map((java.util.function.Function<? super Baskent, ? extends Ulke>) baskent -> (Ulke) baskentMapper.toBaskentResponseDtoList(baskent));
        if (ulkeOptional.isPresent()) {
            ulkeOptional.get().setName(ulkeRequestDto.getName());
            ulkeOptional.get().setNufus(ulkeRequestDto.getNufus());
            ulkeOptional.get().setBaskent(ulkeOptional.get().getBaskent());
            return ulkeMapper.toUlkeResponseDto(ulkeOptional.get());
        }
        return  null;
    }



    public UlkeResponseDto save(UlkeRequestDto ulkeRequestDto) {
       Ulke ulke = ulkeMapper.toUlke(ulkeRequestDto);
        return ulkeMapper.toUlkeResponseDto(ulke);
    }

    public void deleteUlke(Long id) {
        ulkeRepository.deleteById(id);
    }
}
