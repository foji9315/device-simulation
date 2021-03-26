package com.virtualdevice.levelsensor.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class EntityRestTemplateConfig {

    @Bean
    public RestTemplate entityRestTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000)).build();
        return restTemplate;
    }
}
