package com.firat.studentapi.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;                  // token parse/doğrulama burada
    private final UserDetailsService userDetailsService;  // username -> DB user çekmek için


    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1) Header'dan Authorization al
        String authHeader = request.getHeader("Authorization");

        // 2) Token yoksa bu filtre bir şey yapmadan geçer (public endpointler çalışabilsin)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3) "Bearer " kısmını at, token'ı al
        String token = authHeader.substring(7);

        // 4) Token içinden username çek
        String username = jwtService.extractUsername(token);

        // 5) SecurityContext zaten dolu değilse (yani kullanıcı zaten set edilmediyse)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6) DB'den kullanıcıyı çek (Spring Security UserDetails formatında)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 7) Token geçerli mi?
            if (jwtService.isTokenValid(token, userDetails)) {

                // 8) Spring Security'ye "bu kullanıcı authenticate oldu" diye bir Authentication objesi veriyoruz
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,              // principal (kullanıcı)
                                null,                    // credentials (şifre) yok
                                userDetails.getAuthorities() // roller/izinler
                        );

                // 9) Bu request boyunca Spring Security artık kullanıcıyı tanır
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        // 10) Request normal akışına devam eder
        filterChain.doFilter(request, response);
    }


    }

