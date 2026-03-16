package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.ClassDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Class.ClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "class API", description = "APIs for managing classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public ApiResponse<List<ClassDTO>> getAllClasses() {

        return new ApiResponse<>(
                true,
                "Classes retrieved successfully",
                classService.getAllClasses()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ClassDTO> getClass(@PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Class retrieved",
                classService.getClassById(id)
        );
    }

    @GetMapping("/course/{courseId}")
    public ApiResponse<List<ClassDTO>> getClassesByCourse(
            @PathVariable Long courseId) {

        return new ApiResponse<>(
                true,
                "Classes retrieved",
                classService.getClassesByCourse(courseId)
        );
    }

    @PostMapping
    public ApiResponse<ClassDTO> createClass(@RequestBody ClassDTO dto) {

        return new ApiResponse<>(
                true,
                "Class created",
                classService.createClass(dto)
        );
    }

}
