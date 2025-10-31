package com.firat.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component//springde bean oluşsun diye
public class JwtService {

    public static final String SECRET_KEY = "xW4u54U5f8pdCx3NVjXNa8YbqaeyaMh7caOuasKpu2s=";

    public String generateToken(UserDetails userDetails) {
        //**** ornek olsun diye
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("role", "Admin");
        //*******

                return Jwts.builder() // jwt sınıfı
                .setSubject(userDetails.getUsername())//userdetailsten gelen namei atar
                .setIssuedAt(new Date())//tokenın oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)) // tokenın geçerlilik süresini belirliyoruz 2 Saat
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }




    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {  // tokenı çözüyoruz

            Claims claims = Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token).getBody();

            return claimsFunction.apply(claims);
    }


    public String getUserNameByToken(String token) {
        return exportToken(token, Claims::getSubject); //token içindeki subjecti çek

    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = exportToken(token, Claims::getExpiration);
        //şuanki saat 15.38
        //token bitiş saati 15.45
        return new Date().before(expiredDate); //şuanki zaman token vaktinden küçükse aktiftir
    }


    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); //anahtarı kullanarak bir key oluşturduk ve key tipinde geri dönüyoruz
        return  Keys.hmacShaKeyFor(keyBytes);
    }




}
