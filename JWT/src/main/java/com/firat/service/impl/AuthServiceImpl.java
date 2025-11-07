package com.firat.service.impl;

import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;
import com.firat.jwt.AuthResponse;
import com.firat.jwt.JwtService;
import com.firat.model.User;
import com.firat.repository.UserRepository;
import com.firat.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService  jwtService;


    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optionalUserser = userRepository.findByUsername(request.getUsername());
            String token = jwtService.generateToken(optionalUserser.get());

            return new AuthResponse(token);
        }catch (Exception e) {
            System.out.println("Kullanıcı adı veya şifre hatalı!");
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        DtoUser dto = new DtoUser();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser,dto);

        return dto;
    }
}
