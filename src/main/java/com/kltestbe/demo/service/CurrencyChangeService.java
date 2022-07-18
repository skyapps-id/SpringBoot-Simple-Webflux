package com.kltestbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kltestbe.demo.dto.currency_change_api.CurrencyChangeApi;

import reactor.core.publisher.Mono;

@Service
public class CurrencyChangeService {
    @Autowired
    WebClient webClient;

	@Value("${app.api.apilayer}")
	private String apiKey;

    public Mono<CurrencyChangeApi> getCurrencyChange(String src) {
        return webClient.get()
            .uri("https://api.apilayer.com/currency_data/change?source={src}&currencies=IDR", src)
            .header("apikey", apiKey)
            .retrieve()
            .onStatus(
                HttpStatus.UNAUTHORIZED::equals,
                response -> response.bodyToMono(String.class).map(Exception::new)
            )
            .bodyToMono(CurrencyChangeApi.class);
    }
}
