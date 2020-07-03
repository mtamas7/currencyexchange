package com.wup.homework.currencyexchange.api.response;

import com.wup.homework.currencyexchange.api.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRateResponse {
    private String baseCurrency;
    private String currentDate;
    private List<Currency> rates;
}
