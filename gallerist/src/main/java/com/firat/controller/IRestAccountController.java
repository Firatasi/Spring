package com.firat.controller;

import com.firat.dto.DtoAccount;
import com.firat.dto.DtoAccountIU;

public interface IRestAccountController {
    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
