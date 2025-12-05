package com.firat.service;

import com.firat.dto.AuthRequest;
import com.firat.dto.AuthResponse;
import com.firat.dto.DtoUser;
import com.firat.model.RefreshToken;

public interface IAuthenticationService {
    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshToken input);
}
