package com.mark.server.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ConfigurationProperties("data.go.kr")
@ConstructorBinding
@Getter
@ToString
public class WeatherConfig {
    private final Map<String, GoApi> apis;
    private final String baseUrl;

    public WeatherConfig(String baseUrl, List<GoApi> apis) {
        this.baseUrl = baseUrl;
        this.apis = apis.stream().collect(Collectors.toMap(GoApi::getName, i -> i));
    }

    public GoApi getApi(String name) {
        return apis.get(name);
    }
}
