package com.student.mapper;

import com.student.dto.request.StudentRequestDto;
import com.student.dto.response.StudentResponseDto;
import com.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CourseMapper.class})
public interface StudentMapper {
    Student toStudent(StudentRequestDto studentRequestDto);
    @Mapping(source = "studentId", target = "id")//id null dönmesin diye
    StudentResponseDto toStudentResponseDto(Student student);
    List<StudentResponseDto> toStudentResponseDtoList(List<Student> studentList);
}
