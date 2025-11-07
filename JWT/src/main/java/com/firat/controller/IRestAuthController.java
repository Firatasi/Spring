package com.firat.controller;

import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;
import com.firat.jwt.AuthResponse;

public interface IRestAuthController {
    public DtoUser register(AuthRequest authRequest);
    public AuthResponse authenticate(AuthRequest authRequest);
}
