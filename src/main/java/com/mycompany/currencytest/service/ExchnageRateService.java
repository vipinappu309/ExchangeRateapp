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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vipin
 */
public class ExchnageRateService {
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ExchnageRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateResponse getExchangeRates(String base) throws RestClientException {
        String url = String.format("%s?base=%s&apikey=%s", apiUrl, base, apiKey);
        return restTemplate.getForObject(url,ExchangeRateResponse.class);
    }

    public ConvertResponse convertCurrency(ConvertRequest request) throws RestClientException {
        ExchangeRateResponse rates = getExchangeRates(request.getFrom());

        if (rates.getRates() == null || !rates.getRates().containsKey(request.getTo())) {
            throw new CustomException("Currency not supported: " + request.getTo());
        }
        double rate = rates.getRates().get(request.getTo());
        double convertedAmount = request.getAmount() * rate;

        return new ConvertResponse(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                convertedAmount
        );
    }
    
}
