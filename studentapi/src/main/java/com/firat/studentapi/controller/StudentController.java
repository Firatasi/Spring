package com.firat.studentapi.controller;

import com.firat.studentapi.dto.StudentDto;
import com.firat.studentapi.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    // POST /students
    @PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return service.create(dto);
    }

    // GET /students
    @GetMapping
    public List<StudentDto> getAll() {
        return service.getAllStudents();
    }

    // GET /students/{id}
    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // PATCH /students/{id}/grade?value=85
    @PatchMapping("/{id}/grade")
    public StudentDto updateGrade(@PathVariable Long id,
                                  @RequestParam("value") Double grade) {
        return service.updateGrade(id, grade);
    }

    // DELETE /students/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
