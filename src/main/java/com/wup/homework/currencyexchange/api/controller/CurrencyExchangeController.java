package com.wup.homework.currencyexchange.api.controller;

import com.wup.homework.currencyexchange.api.converter.ExchangeRateCategoryConverter;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.request.ConvertCurrencyRequest;
import com.wup.homework.currencyexchange.api.request.LatestCurrencyExchangeRateRequest;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "CurrencyExchangeController", tags = {"Main controller of the application"})
@RestController
public class CurrencyExchangeController {

    /**
     *
     */
    @ApiOperation(value = "List of exchange rates in the requested exchange category", response = ExchangeRateResponse.class, tags = "getExchangeRates")
    @RequestMapping(value = "/latest", params = {"exchangeRate"}, method = GET)
    @ResponseBody
    public ExchangeRateResponse getExchangeRates(@RequestParam("exchangeRate") ExchangeRateCategory exchangeRate) {
        return new ExchangeRateResponse();
    }

    /**
     * @param request
     */
    @PostMapping("/latest")
    public ExchangeRateResponse getExchangeRatesInRequestedCurrency(@Valid @RequestBody LatestCurrencyExchangeRateRequest request) {
        return new ExchangeRateResponse();
    }

    /**
     * @param request
     */
    @PostMapping("/exchange")
    public ConvertCurrencyResponse convertCurrency(@Valid @RequestBody ConvertCurrencyRequest request) {
        return new ConvertCurrencyResponse(DateUtil.getFormattedCurrentDateAsString(), request.getBaseCurrency(), request.getBaseAmount(), request.getTargetCurrency(), 110f);
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(ExchangeRateCategory.class, new ExchangeRateCategoryConverter());
    }

}