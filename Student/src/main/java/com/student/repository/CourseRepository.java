package com.student.repository;

import com.student.entity.Course;
import com.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
