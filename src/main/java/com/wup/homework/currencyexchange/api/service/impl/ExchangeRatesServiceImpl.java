package com.wup.homework.currencyexchange.api.service.impl;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.Currency;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import com.wup.homework.currencyexchange.database.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.wup.homework.currencyexchange.util.Constants.BASE_CURRENCY_CODE;
import static com.wup.homework.currencyexchange.util.CurrencyUtil.getCurrencyRate;
import static com.wup.homework.currencyexchange.util.DateUtil.getFormattedCurrentDateAsString;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRatesServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category) {
        return getExchangeRatesWithBaseCurrency(category);
    }

    @Override
    public ExchangeRateResponse getLatestExchangeRates(ExchangeRateCategory category, String baseCurrency) throws UnsupportedCurrencyException {
        return getExchangeRatesWithRequiredCurrency(category, baseCurrency);
    }

    private ExchangeRateResponse getExchangeRatesWithBaseCurrency(ExchangeRateCategory category) {
        List<Currency> exchangeRates = exchangeRateRepository.findAll()
                .stream()
                .map(currencyRate -> mapCurrencyRateToCurrency(currencyRate, category))
                .filter(currency -> !currency.getCurrencyCode().equals(BASE_CURRENCY_CODE))
                .collect(Collectors.toList());

        return new ExchangeRateResponse(BASE_CURRENCY_CODE, getFormattedCurrentDateAsString(), exchangeRates);
    }

    private ExchangeRateResponse getExchangeRatesWithRequiredCurrency(ExchangeRateCategory category, String baseCurrency) {
        List<CurrencyRate> exchangeRates = exchangeRateRepository.findAll();

        Float requestedCurrencyRateInBaseCurrency = exchangeRates.stream()
                .filter(exchangeRate -> exchangeRate.getCurrencyCode().equals(baseCurrency.toUpperCase()))
                .map(exchangeRate -> getCurrencyRate(exchangeRate, category)).findFirst()
                .orElseThrow(UnsupportedCurrencyException::new);

        List<Currency> calculatedRates = exchangeRates.stream()
                .filter(exchangeRate -> !exchangeRate.getCurrencyCode().equals(baseCurrency.toUpperCase()))
                .map(exchangeRate -> mapCurrencyRateToExchangedCurrencyRate(requestedCurrencyRateInBaseCurrency, category, exchangeRate))
                .collect(Collectors.toList());
        return new ExchangeRateResponse(baseCurrency.toUpperCase(), getFormattedCurrentDateAsString(), calculatedRates);
    }

    private Currency mapCurrencyRateToExchangedCurrencyRate(Float requestedCurrencyRateInBaseCurrency,
                                                            ExchangeRateCategory category,
                                                            CurrencyRate exchangeRate) {
        return new Currency(exchangeRate.getCurrencyCode(),
                exchangeRate.getCurrencyName(),
                getCurrencyRate(exchangeRate, category) / requestedCurrencyRateInBaseCurrency);
    }

    private Currency mapCurrencyRateToCurrency(CurrencyRate currencyRate, ExchangeRateCategory category) {
        return new Currency(currencyRate.getCurrencyCode(),
                currencyRate.getCurrencyName(),
                getCurrencyRate(currencyRate,
                        category));
    }

}
