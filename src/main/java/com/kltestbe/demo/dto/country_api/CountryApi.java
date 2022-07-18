package com.kltestbe.demo.dto.country_api;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CountryApi implements Serializable {
    @JsonProperty("name")
    private CountryName name;

    @JsonProperty("currencies")
    private Map<String, CountryCurrency> currencies;

    @JsonProperty("population")
    private int population;
}
