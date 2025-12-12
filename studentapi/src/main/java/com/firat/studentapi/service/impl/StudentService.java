package com.firat.studentapi.service.impl;

import com.firat.studentapi.dto.StudentDto;
import com.firat.studentapi.entity.Student;
import com.firat.studentapi.repository.StudentRepository;
import com.firat.studentapi.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    @Autowired
    private final StudentRepository repository;

    @Override
    public StudentDto create(StudentDto dto) {
        Student student = new Student();
        BeanUtils.copyProperties(dto, student, "id"); //dto özelliklerini studenta kopyalar

        Student savedStudent = repository.save(student);

        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(savedStudent, studentDto);
        return studentDto;
    }

    @Override
    public List<StudentDto> getAllStudents() {

        return  repository.findAll()
                .stream()
                .map(student-> {
                    StudentDto studentDto = new StudentDto();
                    BeanUtils.copyProperties(student, studentDto);
                    return studentDto;
                }).toList();
    }

    @Override
    public StudentDto getById(long id) {
        Student student = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));

        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    @Override
    public StudentDto updateGrade(Long id, Double grade) {

        Student student = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));

        student.setGrade(grade);
        student =  repository.save(student);
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    @Override
    public void deleteById(long id) {
    if(!repository.existsById(id)){
        throw new RuntimeException("Student not found");
    }
    repository.deleteById(id);
    }
}
