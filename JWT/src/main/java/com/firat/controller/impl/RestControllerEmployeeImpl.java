package com.firat.controller.impl;

import com.firat.controller.IRestControllerEmployee;
import com.firat.dto.DtoEmployee;
import com.firat.service.IEmployeeService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class RestControllerEmployeeImpl implements IRestControllerEmployee {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@NotEmpty @PathVariable(value = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}
