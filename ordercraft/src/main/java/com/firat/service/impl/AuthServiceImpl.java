package com.firat.service.impl;

import com.firat.dto.request.*;
import com.firat.dto.response.TokenResponse;
import com.firat.entity.Role;
import com.firat.entity.User;
import com.firat.exception.DuplicateResourceException;
import com.firat.repository.UserRepository;
import com.firat.security.JwtService;
import com.firat.service.AuthService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void registerCustomer(RegisterRequest request) {
        register(request, Role.CUSTOMER);
    }

    @Override
    public void registerAdmin(RegisterRequest request) {
        register(request, Role.ADMIN);
    }

    private void register(RegisterRequest request, Role role) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + request.getUsername());
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtService.generateToken(auth.getName());
        return TokenResponse.builder().token(token).tokenType("Bearer").build();
    }
}
