package com.mark.server;

import com.mark.server.config.GoApi;
import com.mark.server.config.WeatherConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    WeatherConfig weatherConfig;

    @Test
    @DisplayName("WeatherConfig 가 잘 설정 되어있는지 확인한다")
    void contextLoads() {
        GoApi api = weatherConfig.getApi("초단기예보조회");
        Assertions.assertThat(api.getKey()).isEqualTo("4fWb8%2FkTHYd16gM1uMFhWq50Xm2HFGpJV8Ec189Ux5SJSGh1%2FqxoTdHVpcJurNh0aO6my3yu18j7ZvPMTEfiHg%3D%3D");
        Assertions.assertThat(api.getUrl()).isEqualTo("getUltraSrtFcst");
    }

}
