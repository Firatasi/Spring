package com.firat.controller;

import com.firat.dto.DtoEmployee;

public interface IRestControllerEmployee {
    public DtoEmployee findEmployeeById(Long id);
}
