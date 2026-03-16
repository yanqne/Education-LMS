package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.TeacherDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Teacher.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API", description = "APIs for managing Teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ApiResponse<List<TeacherDTO>> getAllTeachers() {

        List<TeacherDTO> teachers = teacherService.getAllTeachers();

        return new ApiResponse<>(
                true,
                "Teachers retrieved successfully",
                teachers
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<TeacherDTO> getTeacherById(@PathVariable Long id) {

        TeacherDTO teacher = teacherService.getTeacherById(id);

        return new ApiResponse<>(
                true,
                "Teacher retrieved successfully",
                teacher
        );
    }

    @PostMapping
    public ApiResponse<TeacherDTO> createTeacher(
            @RequestBody TeacherDTO teacherDTO) {

        TeacherDTO teacher = teacherService.createTeacher(teacherDTO);

        return new ApiResponse<>(
                true,
                "Teacher created successfully",
                teacher
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<TeacherDTO> updateTeacher(
            @PathVariable Long id,
            @RequestBody TeacherDTO teacherDTO) {

        TeacherDTO teacher = teacherService.updateTeacher(id, teacherDTO);

        return new ApiResponse<>(
                true,
                "Teacher updated successfully",
                teacher
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTeacher(@PathVariable Long id) {

        teacherService.deleteTeacher(id);

        return new ApiResponse<>(
                true,
                "Teacher deleted successfully",
                null
        );
    }
}
