package com.wup.homework.currencyexchange.util;

import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.database.model.CurrencyRate;

public class CurrencyUtil {

    public static Float getCurrencyRate(CurrencyRate currencyRate, ExchangeRateCategory category) {
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
