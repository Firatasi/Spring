package com.firat.services;

import com.firat.entites.Student;
import org.springframework.stereotype.Service;


@Service
public interface IStudentService {


    public Student saveStudent(Student student);

}
