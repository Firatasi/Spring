package com.firat.services.impl;

import com.firat.dto.DtoCourse;
import com.firat.dto.DtoStudent;
import com.firat.dto.DtoStudentIU;
import com.firat.entites.Course;
import com.firat.entites.Student;
import com.firat.repository.StudentRepository;
import com.firat.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<DtoStudent> getAllStudents() {
        List<DtoStudent> dtoList = new ArrayList<>();

        List<Student> studentList =  studentRepository.findAll();
        for (Student student : studentList) {
            DtoStudent dto = new DtoStudent();
            BeanUtils.copyProperties(student, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public DtoStudent getStudentById(Integer id) {
        DtoStudent dtoStudent = new DtoStudent();
        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
        Student dbStudent = optional.get();
        BeanUtils.copyProperties(dbStudent, dtoStudent);

        if (dbStudent.getCourses() != null && !dbStudent.getCourses().isEmpty()) {
            for (Course course : dbStudent.getCourses()) {
            DtoCourse dtoCourse = new DtoCourse();
            BeanUtils.copyProperties(course, dtoCourse);
            dtoStudent.getCourses().add(dtoCourse);
            }
        }

        return dtoStudent;
    }

    @Override
    public void deleteStudentById(Integer id) {
        DtoStudent dtoStudent = getStudentById(id);

        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isPresent()) {
            studentRepository.delete(optional.get());
        }

    }

    @Override
    public DtoStudent updateStudentById(Integer id, DtoStudentIU dtoStudentIU) {

        Optional<Student> optional = studentRepository.findById(id);
        if(optional.isPresent()) {
            DtoStudent dto = new DtoStudent();
            Student dbStudent = optional.get();

            dbStudent.setFirstName(dtoStudentIU.getFirstName());
            dbStudent.setLastName(dtoStudentIU.getLastName());
            dbStudent.setBirthOfDate(dtoStudentIU.getBirthOfDate());

            Student updatedStudent = studentRepository.save(dbStudent);

            BeanUtils.copyProperties(updatedStudent, dto);
            return dto;
        }
        return null;
    }

}
