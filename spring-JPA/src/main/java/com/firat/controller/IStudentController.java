package com.firat.controller;

import com.firat.dto.DtoStudent;
import com.firat.dto.DtoStudentIU;
import com.firat.entites.Student;

import java.util.List;

public interface IStudentController {



    //post için
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);


    //get için
    public List<Student> getAllStudents();

    public Student getStudentById(Integer id);

    public void deleteStudentById(Integer id);

    public Student updateStudentById(Integer id, Student updateStudent);
}
