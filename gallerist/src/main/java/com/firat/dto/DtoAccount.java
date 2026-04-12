package com.firat.dto;

import com.firat.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccount extends DtoBase {

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;

}
