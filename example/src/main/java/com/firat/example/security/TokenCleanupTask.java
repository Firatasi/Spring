package com.firat.example.security;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TokenCleanupTask {

    private JwtService jwtService;

    public TokenCleanupTask(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Scheduled(cron = "0 0 2 * * ?")//Her Gece İkide süresi geçmiş tokenları siler
    public void cleanupExpiredTokens() {
        jwtService.cleanupExpiredTokens();
        System.out.println("Süresi dolmuş tokenlar temizlendi");
    }

}
