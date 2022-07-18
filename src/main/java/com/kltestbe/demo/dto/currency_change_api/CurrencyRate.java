package com.kltestbe.demo.dto.currency_change_api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyRate {
    @JsonProperty("start_rate")
    private double startRate;

    @JsonProperty("end_rate")
    private double endRate;
}

