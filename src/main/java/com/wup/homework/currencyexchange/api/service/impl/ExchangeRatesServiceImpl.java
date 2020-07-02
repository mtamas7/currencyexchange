package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.Currency;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import com.wup.homework.currencyexchange.database.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.wup.homework.currencyexchange.util.DateUtil.getFormattedCurrentDateAsString;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final CurrencyRateRepository currencyRateRepository;

    @Autowired
    public ExchangeRatesServiceImpl(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category) throws UnsupportedCurrencyException {
        return getExchangeRates(category);
    }

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category, String baseCurrency) throws UnsupportedCurrencyException {
        return new ExchangeRateResponse();
    }

    private ExchangeRateResponse getExchangeRates(ExchangeRateCategory category) {
        List<Currency> exchangeRates = currencyRateRepository.findAll()
                .stream()
                .map(currencyRate -> mapCurrencyRateToCurrency(currencyRate, category))
                .collect(Collectors.toList());

        return new ExchangeRateResponse("EUR", getFormattedCurrentDateAsString(), exchangeRates);
    }

    private Currency mapCurrencyRateToCurrency(CurrencyRate currencyRate, ExchangeRateCategory category) {
        return new Currency(currencyRate.getCurrencyCode(), currencyRate.getCurrencyName(), getCurrencyRate(currencyRate, category));
    }

    private Float getCurrencyRate(CurrencyRate currencyRate, ExchangeRateCategory category) {
        switch (category) {
            case BUYING:
                return currencyRate.getBuyingRate();
            case SELLING:
                return currencyRate.getSellingRate();
            default:
                return currencyRate.getCentralRate();
        }
    }
}
