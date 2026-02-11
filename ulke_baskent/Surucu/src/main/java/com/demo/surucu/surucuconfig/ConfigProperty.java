package com.demo.surucu.surucuconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigProperty {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
