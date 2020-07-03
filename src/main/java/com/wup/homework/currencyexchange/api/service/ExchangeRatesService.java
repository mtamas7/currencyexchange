package com.wup.homework.currencyexchange.api.service;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;

/**
 * Service for get the exchange rates
 */
public interface ExchangeRatesService {

    /**
     * Get the exchange rates of the base currency based on the requested ExchangeRateCategory
     *
     * @param category the requested ExchangeRateCategory
     * @return the list of the exchange rates
     */
    ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category);

    /**
     * Get the exchange rates of the requested base currency based on the requested ExchangeRateCategory
     *
     * @param category     the requested ExchangeRateCategory
     * @param baseCurrency the requested base currency
     * @return the list of the exchange rates
     * @throws UnsupportedCurrencyException in case of the base currency contains unsupported currency
     */
    ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category, String baseCurrency) throws UnsupportedCurrencyException;
}
