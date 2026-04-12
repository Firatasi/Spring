package com.demo.yemekmasasi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(description = "User API", version = "1.0"))//swagger tanı
@SpringBootApplication
public class YemekmasasiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YemekmasasiApplication.class, args);
    }

}
