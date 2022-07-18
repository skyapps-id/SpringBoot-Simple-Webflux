package com.kltestbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kltestbe.demo.exception.RecordNotFoundException;
import com.kltestbe.demo.mapper.CountryCurrencyResponse;
import com.kltestbe.demo.service.CountryCurrencyService;

@RestController
@RequestMapping(path = "/api")
public class CountryCurrencyController {
    @Autowired
    CountryCurrencyService countryCurrencyService;

    @GetMapping("/country/{search}")
    public ResponseEntity<CountryCurrencyResponse> getHelo(@PathVariable String search) {
        CountryCurrencyResponse countryCurrencyResponse = countryCurrencyService.getCountryCurrency(search);
        if (countryCurrencyResponse == null) {
            throw new RecordNotFoundException("No value present");
        }
        return ResponseEntity.ok(countryCurrencyResponse);
    }
}
