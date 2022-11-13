package com.mark.server.controller;

import com.mark.server.service.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Weather {

    private final WeatherApi weatherApi;

    @GetMapping("/weather")
    public String getWeatherData(){
        return weatherApi.get(null).toString();
    }
}
