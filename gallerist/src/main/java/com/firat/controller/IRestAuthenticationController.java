package com.firat.controller;

import com.firat.dto.AuthRequest;
import com.firat.dto.AuthResponse;
import com.firat.dto.DtoUser;
import com.firat.model.RefreshToken;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshToken input);

}
