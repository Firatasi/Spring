package com.firat.example.security;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUsernameAndUsedFalse(String username); // kullanılmamış token varsa yenisini üretmemek için

    List<RefreshToken> findAllByUsernameAndRevokedFalse(String username); // kullanıcının aktif tokenlarını çek

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.expiryDate < :now")
    void deleteExpiredTokens(LocalDateTime now); // süresi geçmiş tokenları siler

    @Modifying
    @Transactional
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE RefreshToken rt SET rt.used = true WHERE rt.username = :username")
    void markAllAsUsedByUsername(String username); // kullanıcının bütün tokenlarını kullanıldı olarak işaretler
}