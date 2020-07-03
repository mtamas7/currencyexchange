package com.wup.homework.currencyexchange.api.request;

import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LatestCurrencyExchangeRateRequest {
    @NotNull
    private ExchangeRateCategory exchangeRateCategory;

    @NotBlank
    private String currency;
}
