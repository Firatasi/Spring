package com.firat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.firat"})
@EntityScan(basePackages = {"com.firat"})
@EnableJpaRepositories(basePackages = {"com.firat"})


@SpringBootApplication
public class PageableApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(PageableApplicationStarter.class, args);
    }

}
