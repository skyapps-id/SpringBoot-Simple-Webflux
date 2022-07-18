package com.kltestbe.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kltestbe.demo.dto.country_api.CountryApi;
import com.kltestbe.demo.dto.currency_change_api.CurrencyChangeApi;
import com.kltestbe.demo.mapper.CountryCurrencyResponse;
import com.kltestbe.demo.model.LogCurrency;
import com.kltestbe.demo.repository.LogCurrencyRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService {
    @Autowired
    CountryService countrieService;
    @Autowired
    CurrencyChangeService currencyChangeService;
    @Autowired
    LogCurrencyRepository logCurrencyRepository;

    @Transactional
	public CountryCurrencyResponse getCountryCurrency(String search) {
        List<CountryApi> countriesApi = Flux.from(countrieService.getCountries(search)).collectList().block();
        // Handling if not found record
        if (countriesApi.size() == 0) {
            return null;
        }

        // Get country first
        CountryApi country = countriesApi.get(0);
        String currencyCode = String.join("", country.getCurrencies().keySet());
        String currencyName = country.getCurrencies().get(currencyCode).getName();

        // Currency change to IDR
        CurrencyChangeApi currencyChangeApi = Mono.from(currencyChangeService.getCurrencyChange(currencyCode)).block();
        String keyObj = String.join("", currencyChangeApi.getQuotes().keySet());

        // Save to Presisten
        LogCurrency logCurrency = LogCurrency.builder()
            .search(search)
            .fullName(country.getName().getOfficial())
            .population(country.getPopulation())
            .priod(currencyChangeApi.getStartDate() + " ~ " + currencyChangeApi.getEndDate())
            .currency(currencyCode + " - " + currencyName)
            .rateIDR(currencyChangeApi.getQuotes().get(keyObj).getEndRate()).build();
        logCurrency = logCurrencyRepository.save(logCurrency);

        // To Response
        return CountryCurrencyResponse.builder()
            .fullName(logCurrency.getFullName())
            .population(logCurrency.getPopulation())
            .currency(logCurrency.getCurrency())
            .rateToIDR(logCurrency.getRateIDR())
            .build();
    }
}
