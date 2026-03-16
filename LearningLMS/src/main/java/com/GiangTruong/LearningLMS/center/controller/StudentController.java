package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
@Tag(name = "Student API", description = "APIs for managing students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudent();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
