package com.wup.homework.currencyexchange.api.service;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;

public interface ExchangeRatesService {

    /**
     *
     */
    ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category) throws UnsupportedCurrencyException;

    /**
     *
     */
    ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category, String baseCurrency) throws UnsupportedCurrencyException;
}
