package com.firat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"com.firat"})
@EntityScan(basePackages = {"com.firat"})
@EnableJpaRepositories(basePackages = {"com.firat"})
@SpringBootApplication
public class JwtApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplicationStarter.class, args);
    }

}
