package com.wup.homework.currencyexchange.api.service;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;

/**
 * Service for convert the requested amount of the base currency to the target currency based on the ExchangeRateCategory
 */
public interface ConvertCurrencyService {
    /**
     * @param baseCurrency   the requested base currency
     * @param baseAmount     the requested base amount
     * @param targetCurrency the requested target currency
     * @return the converted rate of the target currency
     * @throws UnsupportedCurrencyException @throws UnsupportedCurrencyException in case of the base or the target currency contains unsupported currency
     */
    ConvertCurrencyResponse convertCurrency(String baseCurrency,
                                            Float baseAmount,
                                            String targetCurrency,
                                            ExchangeRateCategory category)
            throws UnsupportedCurrencyException;
}
