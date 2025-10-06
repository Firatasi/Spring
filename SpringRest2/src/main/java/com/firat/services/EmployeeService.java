package com.firat.services;

import com.firat.model.Employee;
import com.firat.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//kontroller vs hepsi bu katmanda gerçekleşir service ondan sonra repositorye gider işin bütün hamlllığı bu katmanda

@Service//spring contiande service beanı oluşması için kullanılır
public class EmployeeService {

    //autowired ile repository enjekte etmem gerekiyor.
    @Autowired//içi boş olmasın otamatik olarak atama yapması için kullanıyoruz. nullpointer hatası almamak için
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeList() {

        return employeeRepository.getAllEmployeeList();
    }


}
