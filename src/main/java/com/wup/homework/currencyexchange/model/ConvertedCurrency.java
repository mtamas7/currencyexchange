package com.wup.homework.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertedCurrency {
    private String baseCurrency;
    private Float baseAmount;
    private String targetCurrency;
    private Float targetAmount;
}
