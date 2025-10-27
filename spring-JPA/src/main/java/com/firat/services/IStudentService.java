package com.firat.services;

import com.firat.dto.DtoStudent;
import com.firat.dto.DtoStudentIU;
import com.firat.entites.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IStudentService {



    //post için
    public DtoStudent saveStudent(DtoStudentIU student);


    //get için
    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(Integer id);

    public void deleteStudentById(Integer id);

    public DtoStudent updateStudentById(Integer id, DtoStudentIU dtoStudentIU);

}
