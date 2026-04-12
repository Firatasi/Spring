package com.firat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.firat"}) // student sınıfının tablosu databasede oluşturulsun diye burda belirtmemiz gerekiyor
@ComponentScan(basePackages = {"com.firat"}) //bean oluşması için yukaradki ile karıştırma
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.firat"}) //repositoryi tanıması için keliyoruz
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}
