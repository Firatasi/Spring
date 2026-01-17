package com.firat.controller.auth;

import com.firat.dto.request.*;
import com.firat.dto.response.TokenResponse;
import com.firat.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // CUSTOMER register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@Valid @RequestBody RegisterRequest request) {
        authService.registerCustomer(request);
    }

    // DEV/TEST için ADMIN register (gerçek projede kapatılır)
    @PostMapping("/register-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAdmin(@Valid @RequestBody RegisterRequest request) {
        authService.registerAdmin(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
