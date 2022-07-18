package com.kltestbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.kltestbe.demo.dto.country_api.CountryApi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService {
    @Autowired
    WebClient webClient;

    public Flux<CountryApi> getCountries(String search) {
        return webClient.get()
            .uri("https://restcountries.com/v3.1/name/{search}", search)
            .retrieve()
            .onStatus(
                HttpStatus.INTERNAL_SERVER_ERROR::equals,
                response -> response.bodyToMono(String.class).map(Exception::new)
            )
            .bodyToFlux(CountryApi.class)
            .onErrorResume(WebClientResponseException.class, ex -> {
                if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    return Mono.empty();
                }
                return Mono.error(ex);
            });
    }
}
