package com.kltestbe.demo.service;

import com.kltestbe.demo.mapper.CountryCurrencyResponse;

public interface CountryCurrencyService {
    CountryCurrencyResponse getCountryCurrency(String search);
}
