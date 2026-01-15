package com.firat.service;

import com.firat.dto.request.LoginRequest;
import com.firat.dto.request.RegisterRequest;
import com.firat.dto.response.TokenResponse;

public interface AuthService {
    void registerCustomer(RegisterRequest request);
    void registerAdmin(RegisterRequest request); // şimdilik dev/test için
    TokenResponse login(LoginRequest request);
}
