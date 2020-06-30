package com.wup.homework.currencyexchange.api.controller;

import com.wup.homework.currencyexchange.api.converter.ExchangeRateCategoryConverter;
import com.wup.homework.currencyexchange.api.request.ConvertCurrencyRequest;
import com.wup.homework.currencyexchange.api.request.LatestCurrencyExchangeRateRequest;
import com.wup.homework.currencyexchange.model.ConvertedCurrency;
import com.wup.homework.currencyexchange.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.model.ExchangeRates;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CurrencyExchangeController {

    /**
     *
     */
    @RequestMapping(value = "/latest", params = {"exchangeRate"}, method = GET)
    @ResponseBody
    public ExchangeRates getExchangeRates(@RequestParam("exchangeRate") ExchangeRateCategory exchangeRate) {
        return new ExchangeRates();
    }

    /**
     * @param request
     */
    @PostMapping("/latest")
    public ExchangeRates getExchangeRatesInRequestedCurrency(@Valid @RequestBody LatestCurrencyExchangeRateRequest request) {
        return new ExchangeRates();
    }


    /**
     * @param request
     */
    @PostMapping("/exchange")
    public ConvertedCurrency convertCurrency(@Valid @RequestBody ConvertCurrencyRequest request) {
        return new ConvertedCurrency(request.getBaseCurrency(), request.getBaseAmount(), request.getTargetCurrency(), 110f);
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(ExchangeRateCategory.class, new ExchangeRateCategoryConverter());
    }

}