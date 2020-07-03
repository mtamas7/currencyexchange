package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.model.SupportedCurrency;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import com.wup.homework.currencyexchange.database.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wup.homework.currencyexchange.util.CurrencyUtil.getCurrencyRate;
import static com.wup.homework.currencyexchange.util.DateUtil.getFormattedCurrentDateAsString;

@Service
public class ConvertCurrencyServiceImpl implements ConvertCurrencyService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ConvertCurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public ConvertCurrencyResponse convertCurrency(String baseCurrency, Float baseAmount, String targetCurrency, ExchangeRateCategory category)
            throws UnsupportedCurrencyException {

        CurrencyRate baseCurrencyRate = getCurrencyRateByCurrencyCode(baseCurrency);
        CurrencyRate targetCurrencyRate = getCurrencyRateByCurrencyCode(targetCurrency);

        return new ConvertCurrencyResponse(getFormattedCurrentDateAsString(),
                baseCurrency,
                baseAmount,
                targetCurrency,
                (getCurrencyRate(targetCurrencyRate, category) / getCurrencyRate(baseCurrencyRate, category)) * baseAmount);
    }

    private CurrencyRate getCurrencyRateByCurrencyCode(String currency) {
        return exchangeRateRepository.findCurrencyRateByCurrencyCode(
                SupportedCurrency.fromValue(currency).getCurrencyCode());
    }
}
