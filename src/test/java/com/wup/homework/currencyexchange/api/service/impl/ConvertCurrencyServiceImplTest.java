package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConvertCurrencyServiceImplTest {

    private ConvertCurrencyService service;

    @Before
    public void init() {
        service = new ConvertCurrencyServiceImpl();
    }

    @Test(expected = UnsupportedCurrencyException.class)
    public void shouldReturnUnsupportedCurrencyException() {
        service.convertCurrency("USD", 10f, "EUR");
    }
}