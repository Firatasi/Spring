package com.firat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.firat"}) //component anahtarıyla işaretlenmiş olan veya extend etmiş sınıfları bul onları spring cotainde beanların oluşmasını sağla
@SpringBootApplication
public class SpringRest2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringRest2Application.class, args);
    }

}
