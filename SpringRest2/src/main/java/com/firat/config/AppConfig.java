package com.firat.config;

import com.firat.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    //db olmadığı için bu paketi oluşturduk

    @Bean //springte bean oluşması için
    public List<Employee> employeeList() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee("1","Fırat","ASI"));
        employeeList.add(new Employee("2","Haluk","ASI"));
        employeeList.add(new Employee("3","Emine","ASI"));
        employeeList.add(new Employee("4","Melis","ASI"));
        employeeList.add(new Employee("5","Boran","ASI"));
        employeeList.add(new Employee("6","Agit","ASI"));

        return employeeList;
    }
}
