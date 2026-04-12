package com.demo.carapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarRequestDto implements Serializable {
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String marka;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String model;
    @NotNull
    @Positive
    private Integer yil;
    @NotNull
    @Size(max = 30)
    @NotEmpty
    private String renk;

    @NotNull
    @Positive
    BigDecimal fiat;

    Boolean otomatik;

}