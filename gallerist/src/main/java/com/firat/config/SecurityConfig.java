package com.firat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.firat.handler.AuthEntryPoint;
import com.firat.jwt.JWTAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()// CSRF korumasını kapatıyoruz (JWT kullandığımız için gerekli değil)
                .authorizeHttpRequests(request->
                        request.requestMatchers(REGISTER , AUTHENTICATE , REFRESH_TOKEN).permitAll()// Bu üç endpoint token olmadan kullanılabilir
                                .anyRequest()// Diğer tüm endpointlere erişim için JWT zorunlu
                                .authenticated())
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()// Token geçersizse veya yetkisiz erişim olursa çalışacak handler
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Spring Security'de session tutulmasın → tamamen stateless yapı
                .authenticationProvider(authenticationProvider)// Bu projede kullanıcı doğrulama işlemlerini AuthenticationProvider yapacak
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);// UsernamePasswordAuthenticationFilter çalışmadan önce bizim JWT filtresi devreye girsin

        return http.build();// Security chain’i oluşturup geri döner
    }
}



