package com.demo.ulke_baskent.service;

import com.demo.ulke_baskent.dto.request.BaskentRequestDto;
import com.demo.ulke_baskent.dto.response.BaskentResponseDto;
import com.demo.ulke_baskent.entity.Baskent;
import com.demo.ulke_baskent.exception.UserNotFoundException;
import com.demo.ulke_baskent.mapper.BaskentMapper;
import com.demo.ulke_baskent.repository.BaskentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaskentService {
    private final BaskentRepository baskentRepository;
    private final BaskentMapper baskentMapper;

    public BaskentService(BaskentRepository baskentRepository, BaskentMapper baskentMapper) {
        this.baskentRepository = baskentRepository;
        this.baskentMapper = baskentMapper;
    }

    public BaskentResponseDto save(BaskentRequestDto baskentRequestDto) {
        return baskentMapper.toBaskentResponseDto(baskentRequestDto);
    }


    public List<BaskentResponseDto> getAllBaskent() {
    List<Baskent> all =  baskentRepository.findAll();
    return baskentMapper.toBaskentResponseDtoList((Baskent) all);
    }

    public BaskentResponseDto getBaskentById(Long id) {
        Optional<Baskent> dto = Optional.ofNullable(baskentRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
        return baskentMapper.toBaskentResponseDto(dto);

    }

    public BaskentResponseDto updateBaskent(Long id, BaskentRequestDto baskentRequestDto) {
    baskentRepository.findById(id).ifPresent(baskent -> {
        baskent.setName(baskentRequestDto.getName());
        baskentRepository.save(baskent);
    });
    return null;
    }

    public void deleteBaskent(Long id) {

        Baskent baskent = baskentRepository.findById(id).orElse(null);
        if (baskent != null) {
            baskentRepository.delete(baskent);
        }

    }

    public BaskentResponseDto getBaskent(Long id) {
        Baskent baskent = baskentRepository.findById(id).orElse(null);
        return baskentMapper.toBaskentResponseDto(Optional.ofNullable(baskent));
    }

}
