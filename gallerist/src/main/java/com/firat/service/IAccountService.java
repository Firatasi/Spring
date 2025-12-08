package com.firat.service;

import com.firat.dto.DtoAccount;
import com.firat.dto.DtoAccountIU;

public interface IAccountService {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
