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

    // Token gerektirmeyen endpointler
    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";

    // Kullanıcı doğrulama işlemlerinin nasıl yapılacağını belirleyen provider
    @Autowired
    private AuthenticationProvider authenticationProvider;

    // Her istekte JWT token'ı kontrol eden filtre
    @Autowired
    private JWTAuthenticationFilter JWTAuthenticationFilter;

    // Token hatalıysa veya yetkisiz erişim varsa çalışacak handler
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())// CSRF korumasını kapatıyoruz (JWT kullandığımız için gerekli değil)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN).permitAll()// Bu üç endpoint token olmadan kullanılabilir
                        .anyRequest().authenticated()// Diğer tüm endpointlere erişim için JWT zorunlu
                )
                .exceptionHandling(ex -> ex                // Token geçersizse veya yetkisiz erişim olursa çalışacak handler
                        .authenticationEntryPoint(authEntryPoint)
                )
                .sessionManagement(session -> session                // Spring Security'de session tutulmasın → tamamen stateless yapı
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)            // Bu projede kullanıcı doğrulama işlemlerini AuthenticationProvider yapacak
                .addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);            // UsernamePasswordAuthenticationFilter çalışmadan önce bizim JWT filtresi devreye girsin

        return http.build();// Security chain’i oluşturup geri döner
    }

}



