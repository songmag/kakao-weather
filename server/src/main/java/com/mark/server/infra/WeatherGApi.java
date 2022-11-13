package com.mark.server.infra;

import com.mark.server.config.GoApi;
import com.mark.server.config.WeatherConfig;
import com.mark.server.infra.dto.WeatherDTO;
import com.mark.server.model.weather.WeatherInformation;
import com.mark.server.service.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class WeatherGApi implements WeatherApi {

    private final WeatherConfig weatherConfig;
    private final WebClient baseWebClient;

    @Override
    public WeatherInformation get(LocalDateTime weather) {
        GoApi goApi = weatherConfig.getApi("초단기예보조회");
        String format = "%s/%s?dataType=%s&serviceKey=%s&numOfRows=%d&pageNo=%d&base_date=%s&base_time=%s&nx=%s&ny=%s";
        String url = String.format(format,
                weatherConfig.getBaseUrl(),
                goApi.getUrl(),
                "JSON",
                goApi.getKey(),
                1,
                1,
                "20221113",
                "1800",
                "62",
                "126"
                );
        WeatherDTO response = baseWebClient.get()
                .uri(URI.create(url))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherDTO.class)
                .block();
        assert response != null;
        Long count = response.getResponse().getBody().getTotalCount();
        url = String.format(format,weatherConfig.getBaseUrl(),goApi.getUrl(), "JSON", goApi.getKey(), count, 1, "20221113", "1800", "62", "126");
        response = baseWebClient.get()
                .uri(URI.create(url))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherDTO.class)
                .block();

        assert response != null;

        return response.convertTo("송파", LocalDateTime.of(2022,11,13,15,0,0) );
    }
}
