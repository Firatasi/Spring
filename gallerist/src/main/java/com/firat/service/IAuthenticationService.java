package com.firat.service;

import com.firat.dto.AuthRequest;
import com.firat.dto.AuthResponse;
import com.firat.dto.DtoUser;

public interface IAuthenticationService {
    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);
}
