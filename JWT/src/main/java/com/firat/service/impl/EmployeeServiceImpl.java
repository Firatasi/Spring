package com.firat.service.impl;

import com.firat.dto.DtoDepartment;
import com.firat.dto.DtoEmployee;
import com.firat.model.Department;
import com.firat.model.Employee;
import com.firat.repository.EmployeeRepository;
import com.firat.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee findEmployeeById(Long id) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Employee employee = optional.get();
        Department department = employee.getDepartment();
        BeanUtils.copyProperties(department, dtoDepartment);
        BeanUtils.copyProperties(employee, dtoEmployee);

        dtoEmployee.setId(dtoEmployee.getId());
        return dtoEmployee;

    }
}
