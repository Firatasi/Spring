package com.demo.carapi.security;

import com.demo.carapi.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final String SCRET_KEY = "c2l0YWxwaGFiZXRwcm9kdWNlbWFuYWdlZGRhd25hcnRzd3VuZ2RvbGxyb3dzd3VuZ3Y=";//önerilmez envoriment olarak kullanmak daha mantıklı
    private final long ACCESS_TOKEN_VALIDITY = 1000*60*15;
    private final long REFRESH_TOKEN_VALIDITY_DAYS = 7;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }


    //JWT CREATE TOKEN
    public String generateToken(UserDetails userDetails) {//token oluşturuyoruz

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();//kullanıcı rol listesini döner
        String rol = authorities.stream().map(val -> val.getAuthority()).findFirst().orElse(null);


        return Jwts
                .builder()
                .subject(userDetails.getUsername())//kullanııc adı verilir genellike
                .signWith(getSignKey(SCRET_KEY))//tokeen imzalama
                .issuedAt(new Date(System.currentTimeMillis()))//token başlama süresi(başladığı andaki tarih)
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))//15 dakika tokenın bitme süresi
                .claim("role", rol)//rol ataması
                .claim("type", "access")//token tipi
                .compact();
    }

    //create refresh token
    public RefreshToken generateRefreshToken(String username) {
        //kullanıcının eski refresh tokenlarını geçersiz kıl
        refreshTokenRepository.markAllAsUsedByUserName(username);

        //yeni bir refresh token oluştur
        String value = UUID.randomUUID().toString()+"-"+System.currentTimeMillis();//UUID evrensel benzersiz tanımlayıcı
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY_DAYS);//son kullanma tarihi

        RefreshToken refreshToken = new RefreshToken(
                value, username, expiryDate
        );
        return refreshTokenRepository.save(refreshToken);
    }

    //login olduğu anda çalışır token çifti oluşturma (access+refresh)
    public TokenPair generateTokenPair(UserDetails userDetails) {//token çifti refreshtoken veya jwttoken AMAC EŞ ZAMANLI OLARAK İKİ TOKEN OLUSTURMAK
        String accessToken = generateToken(userDetails);
        RefreshToken refreshToken = generateRefreshToken(userDetails.getUsername());
        return new TokenPair(accessToken, refreshToken.getToken());
    }

    //login anında değil amaç sonradan refres token ile yeni access token elde etmek
        //aşağıdaki refreshAccessToken metodunun amacı, bir kullanıcıdan gelen refresh token'ı kontrol edip geçerliyse
        //o kullanıcı adına yeni bir acces token üretme işlemine hazırlık yapmaktır
    //Bu metodun net amacı, token yenileme sürecinde güvenliği sağlamak ve token rotation(dönüşüm) kuralını uygulamaktır
    //Geçerli ve kullanılmamış bir refresh token ile ilişkili kullanıcıyı bulmak
    //ve bu tokeni bir daha kullanılmayacak şekilde işaretlemek
    public String refreshAccessToken(String refreshTokenValue) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue).orElseThrow(() -> new RuntimeException("Refresh token not found"));
        if (refreshToken.isExpired()) {//süresi dolmuş refresh token
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        if (refreshToken.isUsed()) {//kullanılmış refresh token
            throw new RuntimeException("Refresh token is used");
        }

        refreshToken.setUsed(true);//refresh tokenı kullanıldı olarak işaretliyoruz

            refreshTokenRepository.save(refreshToken);//refresh tokenı kaydediyoruz

        return refreshToken.getUserName();
    }

    //RefreshToken doğrulama
    public boolean validateRefreshToken(String refreshTokenValue) {
        return refreshTokenRepository.findByToken(refreshTokenValue)
                .map(token->!token.isExpired() && !token.isUsed())
                .orElse(false);
    }

    //Kullanıcının tüm refresh tokenlarını sil(Logout)
    public void revokeAllRefreshTokens(String username) {
        refreshTokenRepository.deleteByUserName(username);
    }

    //Süresi dolmuş tokenları temizle
    public void cleanupExpiredTokens() {
        refreshTokenRepository.deleteExpiredTokens(LocalDateTime.now());
    }



    private SecretKey getSignKey(String scretKey) {
        byte[] decode = Decoders.BASE64.decode(scretKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(decode);
        return secretKey;
    }

    public String getUsernameFromToken(String token) {//tokendan username çekmek
        return Jwts
                .parser()//token çözme işlemi
                .verifyWith(getSignKey(SCRET_KEY))//token doğrulama
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getRoleFromToken(String token) {//tokendan role çekmek
        Claims claims = Jwts
                .parser()//token çözme işlemi
                .verifyWith(getSignKey(SCRET_KEY))//token doğrulama
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("role", String.class);
    }

    public Date getExpirationDateFromToken(String token) {//tokenin son kullanma tarihi
        return  Jwts
                .parser()
                .verifyWith(getSignKey(SCRET_KEY))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public Boolean isTokenExpired(String token) {//tokenin süresi doldu mu
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {//token geçerli mi değil mi
        String username = getUsernameFromToken(token);
        return (userDetails.getUsername().equals(username) && !isTokenExpired(token));
    }

}
