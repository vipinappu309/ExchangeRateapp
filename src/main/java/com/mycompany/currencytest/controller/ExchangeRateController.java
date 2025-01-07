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

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
/**
 *
 * @author vipin
 */

    @RestController
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchnageRateService exchangeRateService;

    public ExchangeRateController(ExchnageRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/rates")
    public ExchangeRateResponse getExchangeRates(@RequestParam(defaultValue = "USD") String base) throws RestClientException {
        return exchangeRateService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public ConvertResponse convertCurrency(@RequestBody ConvertRequest request) throws RestClientException {
        return exchangeRateService.convertCurrency(request);
    }
    
}
