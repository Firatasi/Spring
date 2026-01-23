package com.demo.yemekmasasi.dto.request;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Kimlik}
 */
@Value
public class KimlikRequestDto implements Serializable {
    String nim;
    String tcNo;
}