/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currencytest.service;
import com.mycompany.currencytest.Dao.ConvertRequest;
import com.mycompany.currencytest.Dao.ConvertResponse;
import com.mycompany.currencytest.Dao.ExchangeRateResponse;
import com.mycompany.currencytest.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vipin
 */
@Service
public class ExchnageRateService {
   @Value("${exchange.api.url}")
    private String exchangeApiUrl;

    private final RestTemplate restTemplate;

    public ExchnageRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch exchange rates for a base currency
    public ConvertResponse getExchangeRates(String baseCurrency) throws RestClientException {
        String url = exchangeApiUrl + "?base=" + baseCurrency;

        ResponseEntity<ConvertResponse> response = restTemplate.getForEntity(url, ConvertResponse.class);

        if (response.getBody() == null) {
            throw new RuntimeException("Error fetching exchange rates");
        }

        return response.getBody();
    }

    // Convert the currency
    public ConvertResponse convertCurrency(ConvertRequest request) throws RestClientException {
        ConvertResponse rates = getExchangeRates(request.getFrom());

        Double fromRate = rates.getConvertedAmount();  // This will be fetched from rates response for 'from' currency
        Double toRate = rates.getConvertedAmount();    // This will be fetched from rates response for 'to' currency

        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Invalid currency codes");
        }

        double convertedAmount = (request.getAmount() / fromRate) * toRate;

        ConvertResponse conversionResponse = new ConvertResponse();
        conversionResponse.setFrom(request.getFrom());
        conversionResponse.setTo(request.getTo());
        conversionResponse.setAmount(request.getAmount());
        conversionResponse.setConvertedAmount(convertedAmount);

        return conversionResponse;
    }
    
}
