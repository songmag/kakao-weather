package com.mark.server.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest
public class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get Mapping Test")
    void testGetMapping() throws Exception {
        String url = "/weather?date=2022-10-03T00:00:00";
        mockMvc.perform(get(url))
                .andDo(print());
    }

}
