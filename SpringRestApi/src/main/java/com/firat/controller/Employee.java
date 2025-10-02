package com.firat.controller;
import com.firat.repository.EmployeeRepository;
import com.firat.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest/api") //bütün metodların başına ekler
public class Employee {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employee-list")
    public List<Employee> getAllEmployeeList() {
        return employeeService.getAllEmployeeList();
    }
}
