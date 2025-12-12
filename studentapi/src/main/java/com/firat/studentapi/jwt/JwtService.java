package com.firat.studentapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "12345678901234567890123456789012"; // en az 32 char

    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        // Token'ın içine koyacağımız ana bilgi: username (subject)

    return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60)) // 1 saat
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token) {
        // Token içinden subject (username) çıkarır
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // token'daki username ile DB'den gelen user aynı mı + token süresi dolmuş mu?
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    private Claims extractAllClaims(String token) {
        // Token parse edilir, imza doğrulanır. İmza yanlışsa burada exception fırlar.

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }






}
