package com.demo.yemekmasasi.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Game}
 */
@Value
public class GameResponseDto implements Serializable {
    String gameName;
}