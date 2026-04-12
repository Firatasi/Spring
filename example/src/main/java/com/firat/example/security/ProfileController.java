package com.firat.example.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProfileController {

    @GetMapping("/profile")
    public Map<String, Object> profile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return Map.of(
                "username", userDetails.getUsername(),
                "roles", userDetails.getAuthorities()
        );
    }
}