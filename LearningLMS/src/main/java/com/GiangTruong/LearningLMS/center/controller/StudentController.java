package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.StudentDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Student.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
@Tag(name = "Student API", description = "APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ApiResponse<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ApiResponse<>(
                true,
                "Student retrieved successfully",
                students
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<StudentDTO> getStudentById(@PathVariable Long id) {

        StudentDTO student = studentService.getStudentById(id);

        return new ApiResponse<>(
                true,
                "Student retrieved successfully",
                student
        );
    }

    @PostMapping
    public ApiResponse<StudentDTO> createStudent(
            @RequestBody StudentDTO studentDTO) {

        StudentDTO student = studentService.createStudent(studentDTO);

        return new ApiResponse<>(
                true,
                "Student created successfully",
                student
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {

        StudentDTO student = studentService.updateStudent(id, studentDTO);

        return new ApiResponse<>(
                true,
                "Student updated successfully",
                student
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return new ApiResponse<>(
                true,
                "Student deleted successfully",
                null
        );
    }
}
