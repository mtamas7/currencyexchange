package com.wup.homework.currencyexchange.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertCurrencyResponse {
    private String currentDate;
    private String baseCurrency;
    private Float baseAmount;
    private String targetCurrency;
    private Float targetAmount;
}
