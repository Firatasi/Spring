package com.demo.ulke_baskent.mapper;

import com.demo.ulke_baskent.dto.request.BaskentRequestDto;
import com.demo.ulke_baskent.dto.response.BaskentResponseDto;
import com.demo.ulke_baskent.entity.Baskent;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BaskentMapper {

    Baskent toBaskent(BaskentRequestDto baskentRequestDto);
    BaskentResponseDto toBaskentResponseDto(BaskentRequestDto baskent);
    List<BaskentResponseDto> toBaskentResponseDtoList(Baskent baskent);
    BaskentResponseDto toBaskentResponseDto(List<Baskent> baskentList);

    <T> BaskentResponseDto toBaskentResponseDto(Optional<T> baskent);
}
