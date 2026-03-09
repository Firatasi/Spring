package com.firat.authlab.security;

import com.firat.authlab.dto.request.UserLoginReq;
import com.firat.authlab.dto.request.UserRegisterReq;
import com.firat.authlab.dto.response.UserAuthResponse;
import com.firat.authlab.entity.Role;
import com.firat.authlab.entity.User;
import com.firat.authlab.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserAuthResponse register(UserRegisterReq request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Bu username zaten kullanılıyor");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kullanılıyor");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken((UserDetails) savedUser);

        return new UserAuthResponse(
                token,
                "Bearer",
                savedUser.getUsername(),
                savedUser.getRole().name()
        );
    }

    public UserAuthResponse login(UserLoginReq request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        String token = jwtService.generateToken((UserDetails) user);

        return new UserAuthResponse(
                token,
                "Bearer",
                user.getUsername(),
                user.getRole().name()
        );
    }
}