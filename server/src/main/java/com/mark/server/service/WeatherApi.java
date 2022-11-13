package com.mark.server.service;

import com.mark.server.model.weather.WeatherInformation;

import java.time.LocalDateTime;

public interface WeatherApi {
    WeatherInformation get(LocalDateTime weather);
}
