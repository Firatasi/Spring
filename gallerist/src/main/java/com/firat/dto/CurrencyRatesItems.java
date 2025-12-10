package com.firat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesItems {

	@JsonProperty("Tarih")
	private String date;
	
	@JsonProperty("TP_DK_USD_A")
	private String usd;
}
