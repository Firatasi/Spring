package com.firat.studentapi.repository;

import com.firat.studentapi.dto.StudentDto;
import com.firat.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {



}
