package com.demo.yemekmasasi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.demo.yemekmasasi.entity.Game}
 */
@Value
public class GameRequestDto implements Serializable {
    @NotNull
    @NotEmpty
    String gameName;
}