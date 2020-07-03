package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import com.wup.homework.currencyexchange.database.repository.ExchangeRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.CENTRAL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ExchangeRatesServiceImplTest {

    private static final String USD_CURRENCY_CODE = "USD";
    private static final String EUR_CURRENCY_CODE = "EUR";
    private static final String HUF_CURRENCY_CODE = "HUF";
    private static final String DUMMY_CURRENCY_CODE = "ASD";

    @Mock
    ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRatesServiceImpl service;

    @Before
    public void init() {
        when(exchangeRateRepository.findAll()).thenReturn(getDummyCurrencyRateList());
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UnsupportedCurrencyException.class)
    public void shouldReturnExchangeRateResponseWithTheSelectedBaseCurrency() {
        service.getLatestExchangeRates(CENTRAL, DUMMY_CURRENCY_CODE);
    }

    @Test
    public void shouldReturnExchangeRateResponseWithBaseCurrency() {
        ExchangeRateResponse response = service.getLatestExchangeRates(CENTRAL);

        assertEquals(2, response.getRates().size());
        assertEquals(Float.valueOf(100), response.getRates().get(0).getRate());
        assertEquals(EUR_CURRENCY_CODE, response.getBaseCurrency());
    }

    @Test
    public void shouldReturnExchangeRateWithRequestedCurrency() {
        ExchangeRateResponse response = service.getLatestExchangeRates(CENTRAL, HUF_CURRENCY_CODE);

        assertEquals(2, response.getRates().size());
        assertEquals(Float.valueOf(10), response.getRates().get(0).getRate());
        assertEquals(HUF_CURRENCY_CODE, response.getBaseCurrency());
    }

    private List<CurrencyRate> getDummyCurrencyRateList() {
        return Arrays.asList(new CurrencyRate(1L, EUR_CURRENCY_CODE, EUR_CURRENCY_CODE, 10f, 10f, 10f),
                new CurrencyRate(1L, USD_CURRENCY_CODE, USD_CURRENCY_CODE, 100f, 100f, 100f),
                new CurrencyRate(1L, HUF_CURRENCY_CODE, HUF_CURRENCY_CODE, 1f, 1f, 1f));
    }
}