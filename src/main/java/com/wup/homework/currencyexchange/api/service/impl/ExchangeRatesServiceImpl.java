package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category) throws UnsupportedCurrencyException {
        return new ExchangeRateResponse();
    }

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category, String baseCurrency) throws UnsupportedCurrencyException {
        return new ExchangeRateResponse();
    }
}
