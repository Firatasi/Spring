package com.demo.carapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // token devre dışı
                .authorizeHttpRequests(request->request
                        //.anyRequest().authenticated()) //kullanıcı hangi istekte bulunursa bulunsun doğrulama yapması gerekiyor
                        .requestMatchers("car/get-cars").authenticated() //giirşi yapmış kullanıcılara izin verir
                        .requestMatchers("car/add-car").hasRole("ADMIN")
                        .requestMatchers("car/delete-car/").hasRole("ADMIN")
                        .anyRequest().denyAll()//bunların dışındaki bütün istekleri reddet
                )

                .httpBasic(Customizer.withDefaults())
                .build();
    }



}
