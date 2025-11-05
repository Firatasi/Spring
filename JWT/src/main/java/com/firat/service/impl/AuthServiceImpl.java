package com.firat.service.impl;

import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;
import com.firat.model.User;
import com.firat.repository.UserRepository;
import com.firat.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
