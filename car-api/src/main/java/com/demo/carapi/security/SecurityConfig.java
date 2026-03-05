package com.demo.carapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable()) // token devre dışı
                .authorizeHttpRequests(authorize->authorize
                        //.anyRequest().authenticated()) //kullanıcı hangi istekte bulunursa bulunsun doğrulama yapması gerekiyor
//                        .requestMatchers("car/get-cars").authenticated() //giirşi yapmış kullanıcılara izin verir
//                        .requestMatchers("car/add-car").hasRole("ADMIN")
//                        .requestMatchers("car/delete-car/").hasRole("ADMIN")
                        .requestMatchers("/profile","/profile2", "/profile3","/profile4").authenticated()//doğrulanmış kişiler erişebilir
                        .requestMatchers("/register","/login","/logout").permitAll()//herkes kayıt olabilir
                        .anyRequest().denyAll()//bunların dışındaki bütün istekleri reddet
                )

                .httpBasic(Customizer.withDefaults())
                .build();
    }



    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {//kimlik doğrulama bunu bile yazmaya gerek yok aslında spring boot otamatiktmen kendisi anlıyor

        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {//kullanıcı ve sıfreyı hashlemek ıcın kullanılır
        return new BCryptPasswordEncoder();
    }



}
