package com.firat.services;

import com.firat.dto.DtoEmployee;
import com.firat.entites.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<DtoEmployee> findAllEmployees();
}
