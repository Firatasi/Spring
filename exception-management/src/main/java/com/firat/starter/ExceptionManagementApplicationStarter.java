package com.firat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.firat"})
@EnableJpaRepositories(basePackages = {"com.firat"})
@ComponentScan(basePackages = {"com.firat"})
@SpringBootApplication
public class ExceptionManagementApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionManagementApplicationStarter.class, args);
    }

}
