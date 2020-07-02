package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import com.wup.homework.currencyexchange.database.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertCurrencyServiceImpl implements ConvertCurrencyService {

    private final CurrencyRateRepository currencyRateRepository;

    @Autowired
    public ConvertCurrencyServiceImpl(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    public ConvertCurrencyResponse convertCurrency(String baseCurrency, Float baseAmount, String targetCurrency) throws UnsupportedCurrencyException {

        throw new UnsupportedCurrencyException();
    }
}
