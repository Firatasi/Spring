package com.demo.carapi.security;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);//token doğruluğunu ve süresini kontrol etmek
    Optional<RefreshToken> findByUserNameAndIsUsedFalse(String userName);//kullanılmamış token varsa yenisini üretmemek içimn

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.expiryDate < :now")
    void deleteExpiredTokens(LocalDateTime now); //süresi geçmiş tokenları siler

    @Modifying
    @Transactional
    void deleteByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE RefreshToken rt SET rt.isUsed = true WHERE rt.userName = :userName")
    void markAllAsUsedByUserName(String userName);//kullanıcının bütün tokenlarını kullanıldı olarak işaretler

}
