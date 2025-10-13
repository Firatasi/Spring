package com.firat.services;

import com.firat.entites.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IStudentService {

    //post için
    public Student saveStudent(Student student);


    //get için
    public List<Student> getAllStudents();

    public Student getStudentById(Integer id);

    public void deleteStudentById(Integer id);

    public Student updateStudentById(Integer id, Student updateStudent);

}
