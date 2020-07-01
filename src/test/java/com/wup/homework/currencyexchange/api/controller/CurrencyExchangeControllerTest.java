package com.wup.homework.currencyexchange.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wup.homework.currencyexchange.api.request.ConvertCurrencyRequest;
import com.wup.homework.currencyexchange.api.request.LatestCurrencyExchangeRateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.wup.homework.currencyexchange.api.model.ExchangeRateCategory.CENTRAL;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyExchangeController.class)
@ActiveProfiles("test")
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
                .content(asJsonString(new ConvertCurrencyRequest("USD", 100f, "EUR")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("baseCurrency")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}