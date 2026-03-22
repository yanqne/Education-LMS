package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.StudentDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Student.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student API", description = "APIs for managing students")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 🎯 GET CURRENT STUDENT
    @GetMapping("/me")
    public ApiResponse<StudentDTO> getCurrentStudent() {
        return new ApiResponse<>(
                true,
                "Get current student",
                studentService.getCurrentStudent()
        );
    }

    // 🎯 CREATE PROFILE
    @PostMapping("/profile")
    public ApiResponse<StudentDTO> createProfile(@RequestBody StudentDTO dto) {
        return new ApiResponse<>(
                true,
                "Profile created successfully",
                studentService.createProfiles(dto)
        );
    }
}