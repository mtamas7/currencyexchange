package com.wup.homework.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ExchangeRateCategory {
    @JsonProperty("selling")
    SELLING("selling"),
    @JsonProperty("buying")
    BUYING("buying"),
    @JsonProperty("central")
    CENTRAL("central");

    private final String exchangeRate;

    public static ExchangeRateCategory fromValue(String value) {
        for (ExchangeRateCategory category : values()) {
            if (category.exchangeRate.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
