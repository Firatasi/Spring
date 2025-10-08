package com.firat.controller;

import com.firat.model.Employee;
import com.firat.model.UpdateEmployeeRequest;
import com.firat.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //spring contiande controller beanı oluşması için kullanılır    //önce controller->service->repostirory->db
@RequestMapping(path = "/rest/api/employee")//gelen bütün isteklerde kök adres tnaımlaması. bütün metodların basına/ rest/api eklendi
public class RestEmployeeController {

    //autowired ile service enjekte etmem gerekiyor.
    @Autowired//içi boş olmasın otamatik olarak atama yapması için kullanıyoruz. nullpointer hatası almamak için
    private EmployeeService employeeService;

    //postmanden istek atıldığında karşılayacak metod
    //bütün listedeki işçileri dön


    @GetMapping(path = "/list")//geti isteği olduğu için getmapping:: postmanden gelen isteği hangi methodla eşleşmesi gerektiğini belirlemek için kullanıyoruz
    public List<Employee> getAllEmployeeList() {
        //isteği aldık bu isteği service yönlendir
        return employeeService.getAllEmployeeList();//**service katmanına geçişş yaptık
    }

    @GetMapping(path = "/list/{id}")
    public Employee getEmployeeById(@PathVariable (name = "id", required = true) String id) {//PATHVERİABLE KULLANIMI İD GELECEK /gelmesi zorunlu olacak required true/:: idye göre veriyi getirecek repodan baslayarak yazmak daha mantıklı
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "/with-params")
    public List<Employee> getEmployeeWithParams(@RequestParam (name = "firstName",required = false) String firstName, //""den sonra reqired false dersem firstname doldurmak zorunda değilim her iki değişken içinde yapılabilir fakat normal değeri her ikisi boş olamaz
                                                @RequestParam (name = "lastName",required = false) String lastName) {
        System.out.println(firstName + " " + lastName);
        return employeeService.getEmployeeWithParams(firstName, lastName);
    }

    @PostMapping(path = "/save-employee")
    public Employee saveEmployee(@RequestBody Employee newEmployee) {

        return employeeService.saveEmployee(newEmployee);
    }


    @DeleteMapping(path = "/delete-employee/{id}")
    public boolean deleteEmployee(@PathVariable(name = "id") String id) {
        return true;
    }

    @PutMapping(path = "/update-employee/{id}") // guncelleme işlermlerinde putmapping kullanılır
    public Employee updateEmployee(@PathVariable(name = "id") String id,@RequestBody UpdateEmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

}
