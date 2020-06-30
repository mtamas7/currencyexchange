package com.wup.homework.currencyexchange.api.request;

import com.wup.homework.currencyexchange.model.ExchangeRateCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatestCurrencyExchangeRateRequest {
    @NotNull
    private ExchangeRateCategory category;

    @NotBlank
    private String currency;
}
