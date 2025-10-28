package com.firat.service;

import com.firat.dto.dtoEmployee;
import com.firat.model.Employee;

public interface IEmployeeService {
    public dtoEmployee findEmployeeById(Long id);
}
