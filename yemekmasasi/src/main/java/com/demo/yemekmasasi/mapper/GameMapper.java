package com.demo.yemekmasasi.mapper;

import com.demo.yemekmasasi.dto.request.GameRequestDto;
import com.demo.yemekmasasi.dto.response.GameResponseDto;
import com.demo.yemekmasasi.entity.Game;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {
    Game toEntity(GameRequestDto gameRequestDto);

    GameRequestDto toDto(Game game);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Game partialUpdate(GameRequestDto gameRequestDto, @MappingTarget Game game);

    Game toEntity(GameResponseDto gameResponseDto);

    GameResponseDto toDto1(Game game);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Game partialUpdate(GameResponseDto gameResponseDto, @MappingTarget Game game);
}