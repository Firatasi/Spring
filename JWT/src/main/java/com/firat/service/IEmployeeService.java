package com.firat.service;

import com.firat.dto.DtoEmployee;

public interface IEmployeeService {
    DtoEmployee findEmployeeById(Long id);
}
