package com.wup.homework.currencyexchange.api.request;

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
public class ConvertCurrencyRequest {
    @NotBlank
    private String baseCurrency;
    @NotNull
    private Float baseAmount;
    @NotBlank
    private String targetCurrency;
}
