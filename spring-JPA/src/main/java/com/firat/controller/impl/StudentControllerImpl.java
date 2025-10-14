package com.firat.controller.impl;

import com.firat.controller.IStudentController;
import com.firat.dto.DtoStudent;
import com.firat.dto.DtoStudentIU;
import com.firat.entites.Student;
import com.firat.services.IStudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping(path = "/save")
    @Override
    public DtoStudent saveStudent(@RequestBody DtoStudentIU dtoStudentIU){
        return studentService.saveStudent(dtoStudentIU);
    }

    @GetMapping(path = "/list")
    @Override
    public List<DtoStudent> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public DtoStudent getStudentById(@PathVariable(name = "id") Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/delete/{id}")
    @Override
    public void deleteStudentById(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public DtoStudent updateStudentById(@PathVariable(name = "id") Integer id,@RequestBody DtoStudentIU dtoStudentIU) {
        return studentService.updateStudentById(id, dtoStudentIU);

    }

}
