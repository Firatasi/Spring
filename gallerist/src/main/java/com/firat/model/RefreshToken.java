package com.firat.model;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class RefreshToken extends BaseEntity{

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expired_date")
    private Date expiredDate;

    @ManyToOne
    private User user;
}
