package com.student.service;

import com.student.dto.request.StudentRequestDto;
import com.student.dto.response.StudentResponseDto;
import com.student.entity.Course;
import com.student.entity.Student;
import com.student.mapper.CourseMapper;
import com.student.mapper.StudentMapper;
import com.student.repository.CourseRepository;
import com.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, CourseMapper courseMapper, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }
    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toStudent(studentRequestDto);

        //Kursları kaydet
        List<Course> kursEntities = studentRequestDto.getKurslar().stream()
                .map(courseMapper::toCourse)
                .toList();
        student.setCourses(kursEntities);

        Student savedStudent =  studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public StudentResponseDto getStudentById(Long id) {
       Student student = studentRepository.findById(id).orElse(null);
       return studentMapper.toStudentResponseDto(student);
    }


    public List<StudentResponseDto> getAllStudents() {
        List<Student> all = studentRepository.findAll();
        return studentMapper.toStudentResponseDtoList(all);
    }
}
