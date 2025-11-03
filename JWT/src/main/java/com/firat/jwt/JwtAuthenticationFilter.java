package com.firat.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override //security sayesinde artık işlem filter katmanından başlıyor controllerdan değil
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header;
        String token;
        String userName;
        // Bearer Fdjsklajfsalkfjskldfhjkldhf(token) çıktı bu şekildedir
        header = request.getHeader("Authorization"); //token değerini döner bu katmanda gelen token doğru mu değil mi bir hata var mı o kontrol edilir



        if (header == null) {
            filterChain.doFilter(request, response);
            return; //hiçbir şey çalışmaz filter katmanına geri döner
        }

        token = header.substring(7);//7. indexten sorna key olduğu için oradan sonrasını vermesi için yapıyoruz BEARERI almıyor

        try{
        userName = jwtService.getUserNameByToken(token); //bu tokendaki usernami çekebiiyor musun bak
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if (userDetails != null && jwtService.isTokenExpired(token)) {
                    //kişiyi security contexe atayabiliriz (controllera geçebilir)

                    UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }catch (ExpiredJwtException e){
            System.out.println("Token Süresi Dolmuş! " + e.getMessage());
        }catch (Exception e){
            System.out.println("Genel Bir Hata Oluştu! " + e.getMessage());
        }

        filterChain.doFilter(request, response);

    }
}
