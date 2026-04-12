package com.firat.service;

import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;

public interface IAuthService {
    public DtoUser register(AuthRequest  authRequest);
}
