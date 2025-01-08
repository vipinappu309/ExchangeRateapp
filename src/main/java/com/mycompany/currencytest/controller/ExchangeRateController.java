/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currencytest.controller;
import com.mycompany.currencytest.Dao.ConvertRequest;
import com.mycompany.currencytest.Dao.ConvertResponse;
import com.mycompany.currencytest.Dao.ExchangeRateResponse;
import com.mycompany.currencytest.exception.CustomException;
import com.mycompany.currencytest.service.ExchnageRateService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
/**
 *
 * @author vipin
 */

    @RestController
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchnageRateService currencyService;

    @Autowired
    public ExchangeRateController(ExchnageRateService currencyService) {
        this.currencyService = currencyService; 
    }

    // GET endpoint for fetching exchange rates
    @GetMapping("/rates")
    public ConvertResponse getExchangeRates(@RequestParam(value = "base", defaultValue = "USD") String baseCurrency) throws RestClientException {
        return currencyService.getExchangeRates(baseCurrency);
    }

    // POST endpoint for converting currency
    @PostMapping("/convert")
    public ConvertResponse convertCurrency(@RequestBody ConvertRequest request) throws RestClientException {
        return currencyService.convertCurrency(request);
    }
}
