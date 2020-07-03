package com.wup.homework.currencyexchange.api.controller;

import com.wup.homework.currencyexchange.api.converter.ExchangeRateCategoryConverter;
import com.wup.homework.currencyexchange.api.exception.UnsupportedCurrencyException;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.request.ConvertCurrencyRequest;
import com.wup.homework.currencyexchange.api.request.LatestCurrencyExchangeRateRequest;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@RestController
public class CurrencyExchangeController {

    final ExchangeRatesService exchangeRatesService;
    final ConvertCurrencyService convertCurrencyService;

    @Autowired
    public CurrencyExchangeController(ConvertCurrencyService convertCurrencyService,
                                      ExchangeRatesService exchangeRatesService) {
        this.convertCurrencyService = convertCurrencyService;
        this.exchangeRatesService = exchangeRatesService;
    }

    /**
     * This endpoint provides the exchange rates of the base currency in the requested ExchangeRateCategory
     *
     * @param exchangeRate the category of the exchange rates
     * @return the list of the exchange rates
     */
    @ApiOperation(value = "List of exchange rates in the requested exchange category", response = ExchangeRateResponse.class, tags = "Get the latest rates")
    @RequestMapping(value = "/latest", params = {"exchangeRate"}, method = GET)
    @ResponseBody
    public ExchangeRateResponse getExchangeRates(@RequestParam("exchangeRate") ExchangeRateCategory exchangeRate) {
        log.debug("Exchange rate request received. Category: " + exchangeRate.getExchangeRate());
        return exchangeRatesService.getLatestExchangeRates(exchangeRate);
    }

    /**
     * This endpoint provides the exchange rates of the requested currency in the requested ExchangeRateCategory
     *
     * @param request contains the requested base currency and the ExchangeRateCategory
     * @return the list of the exchange rates
     * @throws UnsupportedCurrencyException in case of the request contains unsupported currency
     */
    @ApiOperation(value = "List of exchange rates in the requested exchange category based on the requested currency", response = ExchangeRateResponse.class, tags = "Get the latest rates in the requested currency")
    @PostMapping("/latest")
    public ExchangeRateResponse getExchangeRatesInRequestedCurrency(@Valid @RequestBody LatestCurrencyExchangeRateRequest request)
            throws UnsupportedCurrencyException {

        log.debug("Exchange rate request received. Category: "
                + request.getExchangeRateCategory().getExchangeRate()
                + " Base currency: " + request.getCurrency());

        return exchangeRatesService.getLatestExchangeRates(request.getExchangeRateCategory(), request.getCurrency());
    }

    /**
     * This endpoint converts the requested amount of base currency to the requested currency
     *
     * @param request contains the requested base currency, base amount, ExchangeRateCategory and target currency
     * @return the converted rate of the target currency
     * @throws UnsupportedCurrencyException in case of the request contains unsupported currency
     */
    @ApiOperation(value = "Convert the requested amount of the base currency into the requested target currency", response = ConvertCurrencyResponse.class, tags = "Convert currency into the requested currency")
    @PostMapping("/exchange")
    public ConvertCurrencyResponse convertCurrency(@Valid @RequestBody ConvertCurrencyRequest request)
            throws UnsupportedCurrencyException {

        log.debug("Convert currency request received. Category: "
                + request.getExchangeRateCategory().getExchangeRate()
                + " Base currency: " + request.getBaseCurrency()
                + " Base Amount: " + request.getBaseAmount()
                + " Target currency: " + request.getTargetCurrency());

        return convertCurrencyService.convertCurrency(request.getBaseCurrency(),
                request.getBaseAmount(),
                request.getTargetCurrency(),
                request.getExchangeRateCategory());
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(ExchangeRateCategory.class, new ExchangeRateCategoryConverter());
    }

}