package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.CENTRAL;

@RunWith(SpringRunner.class)
public class ExchangeRatesServiceImplTest {

    private ExchangeRatesService service;

    @Before
    public void init() {
        service = new ExchangeRatesServiceImpl();
    }

    @Test
    public void shouldReturnExchangeRateResponseWithBaseCurrency() {
        ExchangeRateResponse response = service.getLatestExchangeRates(CENTRAL);

        assert response instanceof ExchangeRateResponse;
    }

    @Test
    public void shouldReturnExchangeRateResponseWithTheSelectedBaseCurrency() {
        ExchangeRateResponse response = service.getLatestExchangeRates(CENTRAL, "EUR");

        assert response instanceof ExchangeRateResponse;
    }
}