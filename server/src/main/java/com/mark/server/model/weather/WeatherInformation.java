package com.mark.server.model.weather;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString
public class WeatherInformation {
    public String weatherArea;
    public List<RainFall> rainFalls;
    public List<Temperature> temperatures;

    public LocalDateTime time;


    public WeatherInformation(String weatherArea, LocalDateTime time) {
        this.weatherArea = weatherArea;
        this.time = time;
    }

    public void setRainFalls(List<RainFall> rainFalls) {
        this.rainFalls = rainFalls;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    @Data
    @ToString
    public static class Temperature {
        public LocalDateTime time;
        public BigDecimal temperature;

        public Temperature(LocalDateTime time, BigDecimal temperature) {
            this.time = time;
            this.temperature = temperature;
        }
    }

    @Data
    @ToString
    public static class RainFall {
        public LocalDateTime time;
        public String rainFall;

        public RainFall(LocalDateTime time, String rainFall) {
            this.time = time;
            this.rainFall = rainFall;
        }
    }
}
