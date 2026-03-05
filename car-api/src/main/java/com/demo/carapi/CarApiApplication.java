package com.demo.carapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
public class CarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApiApplication.class, args);
    }

}
