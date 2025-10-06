package com.firat.repository;

import com.firat.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository //spring contiande repository beanı oluşması için kullanılır
public class EmployeeRepository {

    @Autowired
    private List<Employee> employeeList; //appconfig db varmıs gıbı ona gitmek için yazdık

    public  List<Employee> getAllEmployeeList() {

        return employeeList;
    }


}
