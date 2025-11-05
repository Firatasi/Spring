package com.firat.controller;

import com.firat.dto.DtoUser;
import com.firat.jwt.AuthRequest;

public interface IRestAuthController {
    public DtoUser register(AuthRequest authRequest);
}
