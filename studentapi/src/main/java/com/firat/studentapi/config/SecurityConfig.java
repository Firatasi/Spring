package com.firat.studentapi.config;


import com.firat.studentapi.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // login/register token istemesin
    private static final String[] PUBLIC_ENDPOINTS = {
            "/auth/register",
            "/auth/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtFilter,          // bizim filter
            AuthenticationProvider authenticationProvider // DaoAuthenticationProvider
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // JWT stateless olduğu için CSRF genelde kapatılır
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // server session tutmayacak
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll() // bunlar açık
                        .anyRequest().authenticated()                  // diğerleri token ister
                )
                .authenticationProvider(authenticationProvider) // login sırasında user+pass kontrol
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // önce JWT kontrol et

        return http.build();
    }
}

