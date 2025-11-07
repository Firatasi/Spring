package com.firat.controller.impl;

import com.firat.controller.IRestAuthController;
import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;
import com.firat.jwt.AuthResponse;
import com.firat.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;
    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest authRequest){
        return authService.register(authRequest);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(request);
    }

}
