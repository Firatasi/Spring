package com.demo.ulke_baskent.service;

import com.demo.ulke_baskent.dto.request.UlkeRequestDto;
import com.demo.ulke_baskent.dto.response.UlkeResponseDto;
import com.demo.ulke_baskent.entity.Ulke;
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

    public UlkeService(UlkeRepository ulkeRepository, UlkeMapper ulkeMapper) {
        this.ulkeRepository = ulkeRepository;
        this.ulkeMapper = ulkeMapper;
    }

    public List<Ulke> getAll() {
        List<Ulke> all = ulkeRepository.findAll();
        List<UlkeResponseDto> ulkeResponseDto = ulkeMapper.toUlkeResponseDtoList(all);
        return ulkeResponseDto;
    }

    public UlkeResponseDto getById(Long id) {
        Ulke ulke= ulkeRepository.findById(id).orElseThrow(null).getUlke();
        if (ulke == null) {
            return null;
        }
        return ulkeMapper.toUlkeResponseDto(ulke);
    }


    public UlkeResponseDto update(Long id, UlkeRequestDto ulkeRequestDto) {
        Optional<Ulke> ulkeOptional = ulkeRepository.findById(id);
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
        ulkeRepository.save(ulke);
        return ulkeMapper.toUlkeResponseDto(ulke);
    }

}
