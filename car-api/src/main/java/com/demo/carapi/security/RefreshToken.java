package com.demo.carapi.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 500)
    private String token;

    private String userName;

    private LocalDateTime expiryDate;

    private LocalDateTime createdDate = LocalDateTime.now();

    private boolean isUsed = false;

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(this.expiryDate);
    }

    public RefreshToken(String token, String userName, LocalDateTime expiryDate) {
        this.token = token;
        this.userName = userName;
        this.expiryDate = expiryDate;
    }
}
