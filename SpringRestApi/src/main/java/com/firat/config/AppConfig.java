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

        employeeList.add(new Employee("1","Fırat","ASI"));
        employeeList.add(new Employee("2","Boran","ASI"));
        employeeList.add(new Employee("1","Melis","ASI"));
        employeeList.add(new Employee("1","Emine","ASI"));
        employeeList.add(new Employee("1","Haluk","ASI"));

        return employeeList;

    }
}
