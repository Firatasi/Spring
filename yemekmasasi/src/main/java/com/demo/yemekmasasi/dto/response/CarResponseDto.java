package com.demo.yemekmasasi.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Car}
 */
@Value
public class CarResponseDto implements Serializable {
    @NotNull
    @Size(max = 50)
    String marka;
    @NotNull
    @Size(max = 50)
    String model;
    Integer yil;
    @Size(max = 30)
    String renk;
    BigDecimal fiat;
    Boolean otomatik;
}