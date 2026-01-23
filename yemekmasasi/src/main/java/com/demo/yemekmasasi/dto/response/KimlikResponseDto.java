package com.demo.yemekmasasi.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Kimlik}
 */
@Value
public class KimlikResponseDto implements Serializable {
    String tcNo;
}