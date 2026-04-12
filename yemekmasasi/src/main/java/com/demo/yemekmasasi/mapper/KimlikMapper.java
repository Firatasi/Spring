package com.demo.yemekmasasi.mapper;

import com.demo.yemekmasasi.dto.request.KimlikRequestDto;
import com.demo.yemekmasasi.dto.response.KimlikResponseDto;
import com.demo.yemekmasasi.entity.Kimlik;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface KimlikMapper {
    Kimlik toEntity(Kimlik kimlikRequestDto);

    KimlikRequestDto toDto(Kimlik kimlik);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Kimlik partialUpdate(KimlikRequestDto kimlikRequestDto, @MappingTarget Kimlik kimlik);

    Kimlik toEntity(KimlikResponseDto kimlikResponseDto);

    KimlikResponseDto toDto1(Kimlik kimlik);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Kimlik partialUpdate(KimlikResponseDto kimlikResponseDto, @MappingTarget Kimlik kimlik);
}