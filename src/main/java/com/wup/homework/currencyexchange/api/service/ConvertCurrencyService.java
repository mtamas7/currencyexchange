package com.wup.homework.currencyexchange.api.service;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;

public interface ConvertCurrencyService {
    /**
     *
     */
    ConvertCurrencyResponse convertCurrency(String baseCurrency, Float baseAmount, String targetCurrency) throws UnsupportedCurrencyException;
}
