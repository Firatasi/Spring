package com.firat.config;

import com.firat.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Employee> employeeList() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(1L, "Fırat"));
        employeeList.add(new Employee(2L, "Boran"));
        employeeList.add(new Employee(3L, "Melis"));
        employeeList.add(new Employee(4L, "Emine"));
        employeeList.add(new Employee(5L, "Haluk"));

        return employeeList;
    }
}
