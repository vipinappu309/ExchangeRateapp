/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currencytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mycompany.currencytest.Dao.ConvertRequest;
import com.mycompany.currencytest.Dao.ConvertResponse;
import com.mycompany.currencytest.service.ExchnageRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.web.client.RestClientException;
/**
 *
 * @author vipin
 */
@SpringBootTest
public class CurrencyServiceTests {
    
    @Autowired
    private ExchnageRateService currencyService;

    @Test
    public void testCurrencyConversion() throws RestClientException {
        ConvertRequest request = new ConvertRequest();
        request.setFrom("USD");
        request.setTo("EUR");
        request.setAmount(100);

        ConvertResponse response = currencyService.convertCurrency(request);

        assertEquals("USD", response.getFrom());
        assertEquals("EUR", response.getTo());
        assertEquals(100, response.getAmount());
        assertEquals(94.5, response.getConvertedAmount(), 0.01);  // Assuming an expected rate of 0.945 for USD to EUR
    }
}
