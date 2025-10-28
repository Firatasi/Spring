package com.firat.controller.impl;

import com.firat.controller.RestEmployeeConrtoller;
import com.firat.dto.dtoEmployee;
import com.firat.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/employee")
public class RestEmployeeControllerImpl implements RestEmployeeConrtoller {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/list/{id}")
    @Override
    public dtoEmployee findEmployeeById(@PathVariable(value = "id") Long id) {


        return employeeService.findEmployeeById(id);
    }


}
