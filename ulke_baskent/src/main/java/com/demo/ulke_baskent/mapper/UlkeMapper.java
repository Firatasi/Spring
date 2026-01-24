package com.demo.ulke_baskent.mapper;

import com.demo.ulke_baskent.dto.request.UlkeRequestDto;
import com.demo.ulke_baskent.dto.response.UlkeResponseDto;
import com.demo.ulke_baskent.entity.Ulke;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UlkeMapper {
    Ulke toUlke(UlkeRequestDto ulkeRequestDto);
    //@Mapping(source = "baskent.id", target = "baskentId")
    //@Mapping(source = "baskent.name", target = "baskentName")

    UlkeResponseDto toUlkeResponseDto(Ulke ulke);

    List<UlkeResponseDto> toUlkeResponseDtoList(List<Ulke> ulkeList);
}
