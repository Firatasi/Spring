package com.firat.studentapi.service;

import com.firat.studentapi.dto.StudentDto;

import java.util.List;

public interface IStudentService {

    StudentDto create(StudentDto dto);

    List<StudentDto> getAllStudents();

    StudentDto getById(long id);

    StudentDto updateGrade(Long id,Double grade); // not güncelle

    void deleteById(long id);

}
