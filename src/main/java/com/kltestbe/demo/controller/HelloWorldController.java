package com.kltestbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kltestbe.demo.service.CountryService;
import com.kltestbe.demo.service.CurrencyChangeService;

@RestController
@RequestMapping(path="/api", produces="application/json")
@CrossOrigin(origins="*")
public class HelloWorldController {
    @Autowired
    CurrencyChangeService currencyChangeService;
    @Autowired
    CountryService countrieService;

    // @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/helo")
    public ResponseEntity<String> getHelo() {
        return ResponseEntity.ok("helo");
    }
}
