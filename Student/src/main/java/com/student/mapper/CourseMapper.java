package com.student.mapper;

import com.student.dto.request.CourseRequestDto;
import com.student.dto.response.CourseResponseDto;
import com.student.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface CourseMapper {
    @Mapping(source = "kursName", target = "name")
    @Mapping(source = "kursKapasite", target = "kapasite")
    Course toCourse(CourseRequestDto courseRequestDto);

    @Mapping(source = "courseId", target = "id")
    @Mapping(source = "name", target = "kursName")
    @Mapping(source = "kapasite", target = "kursKapasite")
    CourseResponseDto toCourseResponseDto(Course course);

    List<CourseResponseDto> toCourseResponseDto(List<Course> courses);
}
