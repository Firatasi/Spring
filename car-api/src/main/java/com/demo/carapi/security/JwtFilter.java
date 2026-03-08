package com.demo.carapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    public JwtFilter(JwtService jwtService, MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { //authorization null sa veya bearer ile başlamıyorsa işlemi sonlandır
            filterChain.doFilter(request, response);
            return;
        }
        //header varsa tokeni alıyoruz, username alıyoruz
        String token = authHeader.substring(7);
        String username = jwtService.getUsernameFromToken(token);//geriye kullanıcı adı döner jwt servicede kendimiz yazmıstık

        if (username != null || SecurityContextHolder.getContext().getAuthentication() == null) { //username varsa ve henüz authentication olmamışsa devam et

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username); //bu isimde biri var mı varsa dön

            if(jwtService.validateToken(token, userDetails)) {//token geçerli mi değil mi HER İSTEKTE TOKEN KONTROL EDİLEREK KULLANICININ KİMLİĞİ DOĞRULANIR
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());//credentials şifre kısmı ve mutlaka null dönülmeli güvenlik için

                SecurityContextHolder.getContext().setAuthentication(authentication); //KULLANICI DOĞRULANMIŞTIR GÜVENİLİRDİR ANLAMINA GELİYOR
            }

        }

        filterChain.doFilter(request, response);

    }
}
