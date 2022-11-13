package com.mark.server.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mark.server.model.weather.WeatherInformation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class WeatherDTO {

    private Response response;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");


    public WeatherInformation convertTo(String weatherArea, LocalDateTime weatherFixTime) {
        WeatherInformation information = new WeatherInformation(weatherArea, weatherFixTime);

        var rainFalls = this.response.body.items.item.stream().filter(i -> i.category.equals("RN1")).map(i -> new WeatherInformation.RainFall(
                LocalDateTime.of(LocalDate.from(dateFormatter.parse(i.fcstDate)), LocalTime.from(timeFormatter.parse(i.fcstTime))),
                i.fcstValue
        )
        ).collect(Collectors.toList());

        var temperatures = this.response.body.items.item.stream().filter(i -> i.category.equals("T1H")).map(i -> new WeatherInformation.Temperature(
                        LocalDateTime.of(LocalDate.from(dateFormatter.parse(i.fcstDate)), LocalTime.from(timeFormatter.parse(i.fcstTime))),
                        new BigDecimal(i.fcstValue)
                )
        ).collect(Collectors.toList());

        information.setRainFalls(rainFalls);
        information.setTemperatures(temperatures);
        return information;
    }

    @NoArgsConstructor
    @Getter
    public static class Response {
        private Header header;
        private Body body;
    }

    @NoArgsConstructor
    @Getter
    public static class Header {
        private String resultCode;
        private String resultMsg;

        public Header(String resultCode, String resultMsg) {
            this.resultCode = resultCode;
            this.resultMsg = resultMsg;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class Body {
        private String dataType;

        private Long pageNo;
        private Long numOfRows;
        private Long totalCount;

        private WeatherItems items;

        public Body(String dataType, Long pageNo, Long numOfRows, Long totalCount, WeatherItems items) {
            this.dataType = dataType;
            this.pageNo = pageNo;
            this.numOfRows = numOfRows;
            this.totalCount = totalCount;
            this.items = items;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class WeatherItems {
        private List<WeatherItem> item;
    }

    @NoArgsConstructor
    @Getter
    public static class WeatherItem {
        private String baseDate;
        private String baseTime;
        private String fcstDate;
        private String fcstTime;
        private String category;
        private String fcstValue;
        private String nx;
        private String ny;

        public WeatherItem(String baseDate, String baseTime, String fcstDate, String fcstTime, String category, String fcstValue, String nx, String ny) {
            this.baseDate = baseDate;
            this.baseTime = baseTime;
            this.fcstDate = fcstDate;
            this.fcstTime = fcstTime;
            this.category = category;
            this.fcstValue = fcstValue;
            this.nx = nx;
            this.ny = ny;
        }
    }

}
