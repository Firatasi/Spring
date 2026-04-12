package com.firat.controller;

import com.firat.dto.dtoEmployee;
import com.firat.model.RootEntity;


public interface RestEmployeeConrtoller {
    public RootEntity<dtoEmployee> findEmployeeById(Long id);
}
