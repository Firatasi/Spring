package com.firat.services.impl;

import com.firat.dto.DtoStudent;
import com.firat.dto.DtoStudentIU;
import com.firat.entites.Student;
import com.firat.repository.StudentRepository;
import com.firat.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceimpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {
        DtoStudent response = new DtoStudent();
        Student student = new Student();
        BeanUtils.copyProperties(dtoStudentIU, student);

        Student dbStudent = studentRepository.save(student);
        BeanUtils.copyProperties(dbStudent, response);
        return response;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList =  studentRepository.findAll();
        return studentList;
    }

    @Override
    public Student getStudentById(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isPresent()) { //veri bulundu mu
            return optional.get();
        }
        return null;
    }

    @Override
    public void deleteStudentById(Integer id) {
        Student dbStudent = getStudentById(id);
        if(dbStudent != null) {
            studentRepository.delete(dbStudent);
        }
    }

    @Override
    public Student updateStudentById(Integer id, Student updateStudent) {
        Student dbStudent = getStudentById(id);
        if(dbStudent != null) {
            dbStudent.setFirstName(updateStudent.getFirstName());
            dbStudent.setLastName(updateStudent.getLastName());
            dbStudent.setBirthOfDate(updateStudent.getBirthOfDate());

            return studentRepository.save(dbStudent);
        }
        return null;
    }

}
