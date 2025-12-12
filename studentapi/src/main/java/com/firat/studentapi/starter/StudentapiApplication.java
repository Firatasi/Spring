package com.firat.studentapi.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.firat.studentapi"})
@EntityScan(basePackages = {"com.firat.studentapi"})
@EnableJpaRepositories(basePackages = {"com.firat.studentapi"})
@SpringBootApplication
public class StudentapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentapiApplication.class, args);
	}

}


