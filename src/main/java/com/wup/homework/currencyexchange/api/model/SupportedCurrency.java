package com.wup.homework.currencyexchange.api.model;

import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupportedCurrency {
    EUR("EUR"),
    USD("USD"),
    GBP("GPB"),
    CHF("CHF"),
    HUF("HUF");

    private final String currencyCode;

    public static SupportedCurrency fromValue(String value) {
        for (SupportedCurrency currency : values()) {
            if (currency.currencyCode.equalsIgnoreCase(value)) {
                return currency;
            }
        }
        throw new UnsupportedCurrencyException();
    }
}
