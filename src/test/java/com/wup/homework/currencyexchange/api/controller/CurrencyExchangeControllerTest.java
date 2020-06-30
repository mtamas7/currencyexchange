package com.wup.homework.currencyexchange.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyExchangeController.class)
@ActiveProfiles("test")
class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnLatestExchangeRates() throws Exception {
        mockMvc.perform(get("/latest")).andExpect(status().is4xxClientError());
    }

}