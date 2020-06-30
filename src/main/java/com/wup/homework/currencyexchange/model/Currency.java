package com.wup.homework.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private String name;
    private Float centralRate;
    private Float buyingRate;
    private Float sellingRate;
}
