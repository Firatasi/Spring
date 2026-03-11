package com.firat.example.security;

import com.firat.example.entity.Role;
import com.firat.example.entity.User;
import com.firat.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Bu kullanıcı zaten kayıtlı");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .enabled(true)
                .accountNonLocked(true)
                .build();

        userRepository.save(user);
        return "Kayıt başarılı";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .username(userDetails.getUsername())
                .build();
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        refreshTokenService.markAsUsed(refreshToken);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(refreshToken.getUsername());

        String newAccessToken = jwtService.generateAccessToken(userDetails);
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken.getToken())
                .username(userDetails.getUsername())
                .build();
    }

    @PostMapping("/logout")
    public String logout(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        refreshToken.setRevoked(true);
        refreshTokenService.markAsUsed(refreshToken);
        return "Logout başarılı";
    }
}