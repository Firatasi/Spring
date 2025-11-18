package com.firat.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Firat"))
                .description("Swagg")
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Component().addSecuritySchemes("berabAuth",
                        new SecurityScheme().type(SecuritySchemeType.HTTP).scheme("aaaa").bearerFormat("JWT")));
    }
}
