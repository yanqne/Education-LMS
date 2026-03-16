package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.CourseDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Course.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "course API", description = "APIs for managing courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ApiResponse<List<CourseDTO>> getAllCourses() {

        List<CourseDTO> courses = courseService.getAllCourses();

        return new ApiResponse<>(
                true,
                "Courses retrieved successfully",
                courses
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<CourseDTO> getCourseById(@PathVariable Long id) {

        CourseDTO course = courseService.getCourseById(id);

        return new ApiResponse<>(
                true,
                "Course retrieved successfully",
                course
        );
    }

    @PostMapping
    public ApiResponse<CourseDTO> createCourse(
            @RequestBody CourseDTO courseDTO) {

        CourseDTO course = courseService.createCourse(courseDTO);

        return new ApiResponse<>(
                true,
                "Course created successfully",
                course
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO courseDTO) {

        CourseDTO course = courseService.updateCourse(id, courseDTO);

        return new ApiResponse<>(
                true,
                "Course updated successfully",
                course
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return new ApiResponse<>(
                true,
                "Course deleted successfully",
                null
        );
    }
}