package com.firat.repository;

import com.firat.services.EmployeeService;
import com.firat.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Repository //spring contiande repository beanı oluşması için kullanılır
public class EmployeeRepository {

    @Autowired
    private List<Employee> employeeList; //appconfig db varmıs gıbı ona gitmek için yazdık

    public  List<Employee> getAllEmployeeList() {

        return employeeList;
    }

    public Employee getEmployeeById(String id) {  //PATHVERİABLE KULLANIMI İD GELECEK gelmesi zorunlu olacak required true::
        Employee findEmployee = null;
        for (Employee employee : employeeList) {
            if (id.equals(employee.getId())) {
                findEmployee = employee;
                break;
            }
        }
        return findEmployee;
    }

    public List<Employee> getEmployeeWithParams(String firstName, String lastName) {
        List<Employee> employeeWithParams = new ArrayList<>();

        if (firstName == null && lastName == null) {
            return employeeList;
        }
        for (Employee employee : employeeList) {
            if (firstName != null && lastName!= null){
                if (employee.getFirstName().equalsIgnoreCase(firstName)&&employee.getLastName().equalsIgnoreCase(lastName)) {
                    employeeWithParams.add(employee);
                }
            }

            if (firstName != null && lastName == null) {
                if (employee.getFirstName().equalsIgnoreCase(firstName)) {
                    employeeWithParams.add(employee);
                }
            }

            if (firstName == null && lastName != null) {
                if (employee.getFirstName().equalsIgnoreCase(lastName)) {
                    employeeWithParams.add(employee);
                }
            }

        }

        return employeeWithParams;
    }

}