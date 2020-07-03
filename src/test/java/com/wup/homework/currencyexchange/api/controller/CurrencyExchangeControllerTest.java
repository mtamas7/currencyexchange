package com.wup.homework.currencyexchange.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wup.homework.currencyexchange.api.model.ExchangeRateCategory;
import com.wup.homework.currencyexchange.api.request.ConvertCurrencyRequest;
import com.wup.homework.currencyexchange.api.request.LatestCurrencyExchangeRateRequest;
import com.wup.homework.currencyexchange.api.response.ConvertCurrencyResponse;
import com.wup.homework.currencyexchange.api.response.ExchangeRateResponse;
import com.wup.homework.currencyexchange.api.service.ConvertCurrencyService;
import com.wup.homework.currencyexchange.api.service.ExchangeRatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.CENTRAL;
import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.SELLING;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ExchangeRatesService exchangeRatesService;

    @Mock
    private ConvertCurrencyService convertCurrencyService;

    @InjectMocks
    private CurrencyExchangeController currencyExchangeController;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void init() {
        initMocks();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(currencyExchangeController)
                .build();
    }

    @Test
    void shouldReturnClientErrorIfTheExchangeCategoryIsNotPresent() throws Exception {
        mockMvc.perform(get("/latest")).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnExchangeRateResponseWithBaseCurrencyIfTheRequiredDataIsPresent() throws Exception {
        mockMvc.perform(get("/latest")
                .param("exchangeRate", "central"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnClientErrorIfTheRequiredDataForLatestRatesIsNotPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/latest")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnExchangeRateResponseWithThePreferredCurrencyIfTheRequiredDataIsPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/latest")
                .content(asJsonString(new LatestCurrencyExchangeRateRequest(CENTRAL, "USD")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("baseCurrency")));
    }

    @Test
    public void shouldReturnClientErrorIfTheRequiredDataForExchangeIsNotPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/exchange")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnConvertCurrencyResponseIfTheRequiredDataIsPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/exchange")
                .content(asJsonString(new ConvertCurrencyRequest("USD", 100f, "EUR", SELLING)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("baseCurrency")));
    }

    private void initMocks() {
        when(exchangeRatesService.getLatestExchangeRates(Mockito.any(ExchangeRateCategory.class))).thenReturn(new ExchangeRateResponse("", "", Collections.emptyList()));
        when(exchangeRatesService.getLatestExchangeRates(Mockito.any(ExchangeRateCategory.class), anyString())).thenReturn(new ExchangeRateResponse("", "", Collections.emptyList()));
        when(convertCurrencyService.convertCurrency(anyString(), anyFloat(), anyString(), Mockito.any(ExchangeRateCategory.class))).thenReturn(new ConvertCurrencyResponse("", "", 0f, "", 0f));
    }

}