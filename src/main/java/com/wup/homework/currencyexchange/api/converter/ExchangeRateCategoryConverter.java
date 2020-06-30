package com.wup.homework.currencyexchange.api.converter;

import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;

import java.beans.PropertyEditorSupport;

public class ExchangeRateCategoryConverter extends PropertyEditorSupport {

    public void setAsText(final String category) throws IllegalArgumentException {
        setValue(ExchangeRateCategory.fromValue(category));
    }

}