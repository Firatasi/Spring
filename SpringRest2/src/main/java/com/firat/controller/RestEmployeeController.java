package com.firat.controller;

import com.firat.model.Employee;
import com.firat.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //spring contiande controller beanı oluşması için kullanılır    //önce controller->service->repostirory->db
@RequestMapping(path = "/rest/api")//gelen bütün isteklerde kök adres tnaımlaması. bütün metodların basına/ rest/api eklendi
public class RestEmployeeController {

    //autowired ile service enjekte etmem gerekiyor.
    @Autowired//içi boş olmasın otamatik olarak atama yapması için kullanıyoruz. nullpointer hatası almamak için
    private EmployeeService employeeService;

    //postmanden istek atıldığında karşılayacak metod
    //bütün listedeki işçileri dön


    @GetMapping(path = "/employee-list")//geti isteği olduğu için getmapping:: postmanden gelen isteği hangi methodla eşleşmesi gerektiğini belirlemek için kullanıyoruz
    public List<Employee> getAllEmployeeList() {
        //isteği aldık bu isteği service yönlendir
        return employeeService.getAllEmployeeList();//**service katmanına geçişş yaptık
    }

}
