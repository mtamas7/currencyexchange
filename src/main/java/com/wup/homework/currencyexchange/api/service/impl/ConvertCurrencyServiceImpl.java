package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import org.springframework.stereotype.Service;

@Service
public class ConvertCurrencyServiceImpl implements ConvertCurrencyService {

    @Override
    public ConvertCurrencyResponse convertCurrency(String baseCurrency, Float baseAmount, String targetCurrency) throws UnsupportedCurrencyException {
        throw new UnsupportedCurrencyException();
    }
}
