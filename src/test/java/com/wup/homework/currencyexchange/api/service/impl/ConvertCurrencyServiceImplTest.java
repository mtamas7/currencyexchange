package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import com.wup.homework.currencyexchange.database.repository.ExchangeRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.SELLING;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ConvertCurrencyServiceImplTest {
    private static final String USD_CURRENCY_CODE = "USD";
    private static final String EUR_CURRENCY_CODE = "EUR";
    private static final String DUMMY_CURRENCY_CODE = "ASD";

    @Mock
    ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ConvertCurrencyServiceImpl service;

    @Before
    public void init() {
        when(exchangeRateRepository.findCurrencyRateByCurrencyCode(USD_CURRENCY_CODE)).thenReturn(getDummyCurrencyRate(USD_CURRENCY_CODE, 100f));
        when(exchangeRateRepository.findCurrencyRateByCurrencyCode(EUR_CURRENCY_CODE)).thenReturn(getDummyCurrencyRate(EUR_CURRENCY_CODE, 10f));
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UnsupportedCurrencyException.class)
    public void shouldReturnUnsupportedCurrencyExceptionInCaseOfUnsupportedCurrency() {
        service.convertCurrency(DUMMY_CURRENCY_CODE, 10f, EUR_CURRENCY_CODE, SELLING);
    }

    @Test
    public void shouldReturnConvertCurrencyResponseWithCorrectData() {

        ConvertCurrencyResponse response = service.convertCurrency(EUR_CURRENCY_CODE, 42f, USD_CURRENCY_CODE, SELLING);

        assertEquals(Float.valueOf(420), response.getTargetAmount());

    }

    private CurrencyRate getDummyCurrencyRate(String currencyCode, Float rate) {
        return new CurrencyRate(1L, currencyCode, currencyCode, rate, rate, rate);
    }
}