package com.firat.services;

import com.firat.controller.Employee;
import com.firat.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;//autwired eklenmezse null point hatası yersin


    public List<Employee> getAllEmployeeList() {
        return employeeRepository.getAllEmployeeList();
    }
}
