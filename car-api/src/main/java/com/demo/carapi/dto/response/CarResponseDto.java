package com.demo.carapi.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto implements Serializable {
    @NotNull
    @Size(max = 50)
    String marka;
    @NotNull
    @Size(max = 50)
    private String model;
    private Integer yil;
    @Size(max = 30)
    private String renk;
    private BigDecimal fiat;
    private Boolean otomatik;
    private Instant eklenmteTarihi;

}