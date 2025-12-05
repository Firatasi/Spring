package com.firat.controller;

import com.firat.dto.AuthRequest;
import com.firat.dto.AuthResponse;
import com.firat.dto.DtoUser;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

}
