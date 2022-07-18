package com.kltestbe.demo.dto.currency_change_api;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyChangeApi implements Serializable {
    private boolean success;

    private boolean change;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("source")
    private String source;

    @JsonProperty("quotes")
    private Map<String, CurrencyRate> quotes;
}
