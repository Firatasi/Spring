package com.firat.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {
//1
    public static final String SCRET_KEY = "DBTQTZxf9xRBzYo/3MxOVOeeLoigYk4q2LmxD3YjIUY=";

    public String generateToken(UserDetails userDetails){ //token oluşturma

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())//token ne zaman oluşturuldu
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) //token bitiş süresi 2 saat
                .signWith(getKey(),SignatureAlgorithm.HS256) //gett keyi çağırdık
                .compact();
    }

    public Key getKey() { //java securityden geliyor key
        byte[] bytes = Decoders.BASE64.decode(SCRET_KEY); // byte şeklinde döner
         return Keys.hmacShaKeyFor(bytes);//byte key şeklinde çevirip döner
    }

    public Claims getClaims(String token){//token içindeki bilgileri okumak için yazarız

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJwt(token).getBody();
        return claims;
    }

    public <T> T exportToken(String token, Function<Claims,T> claimsFunc) {

        Claims claims = getClaims(token);
        return claimsFunc.apply(claims); //tokenı çözer ve geriye döner

    }

    public String getUsernameByToken(String token){
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);//şuanın tarihi token oluşturulma tarihinden küçükse token geçerlidir.
    }

}
