package com.kltestbe.demo.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryCurrencyResponse {
    private String fullName;

    private int population;

    private String currency;

    private double rateToIDR;
}
