package com.firat.service;

import com.firat.dto.request.StudentRequestCreate;
import com.firat.dto.response.StudentResponse;
import com.firat.entity.Student;
import com.firat.exception.DuplicateResourceException;
import com.firat.exception.NotFoundException;
import com.firat.mapper.StudentMapper;
import com.firat.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse create(StudentRequestCreate studentRequestCreate) {
        studentRepository.findByEmail(studentRequestCreate.getEmail()).ifPresent(student -> {
            throw new DuplicateResourceException("Student already exists" + studentRequestCreate.getEmail());
        });

        Student student = StudentMapper.toEntity(studentRequestCreate);
        Student saved =  studentRepository.save(student);

        return StudentMapper.toResponse(saved);
    }

    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Student not found" + id));

        return StudentMapper.toResponse(student);
    }

    public void delete (Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Student not found" + id));
        studentRepository.deleteById(student.getId());
    }



}
