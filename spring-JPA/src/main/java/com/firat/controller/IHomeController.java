package com.firat.controller;

import com.firat.dto.DtoHome;

public interface IHomeController {
    public DtoHome findHomeById(Long id);
}
