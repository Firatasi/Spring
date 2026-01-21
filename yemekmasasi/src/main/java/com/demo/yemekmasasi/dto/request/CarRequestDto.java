package com.demo.yemekmasasi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Car}
 */
@Value
public class CarRequestDto implements Serializable {
    @NotNull
    @Size(max = 50)
    @NotEmpty
    String marka;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    String model;
    @NotNull
    @Positive
    Integer yil;
    @NotNull
    @Size(max = 30)
    @NotEmpty
    String renk;
    @NotNull
    @Positive
    BigDecimal fiat;
    Boolean otomatik;
    @NotNull
    Instant eklenmteTarihi;
}