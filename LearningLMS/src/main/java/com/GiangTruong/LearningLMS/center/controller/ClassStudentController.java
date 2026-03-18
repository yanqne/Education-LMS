package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.ClassStudent.ClassStudentReq;
import com.GiangTruong.LearningLMS.center.dto.ClassStudentRes;
import com.GiangTruong.LearningLMS.center.dto.EnrollReq;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.ClassStudent.ClassStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-students")
@RequiredArgsConstructor
@Tag(name = "classStudents API", description = "APIs for managing classStudents")
public class ClassStudentController {

    private final ClassStudentService classStudentService;

    // Đăng ký học viên vào lớp
    @PostMapping("/classes/{classId}/students")
    public ApiResponse<ClassStudentRes> registerStudent(
            @PathVariable Long classId,
            @RequestBody ClassStudentReq request
    ) {

        request.setClassId(classId);

        ClassStudentRes response = classStudentService.registerStudent(request);

        return new ApiResponse<>(
                true,
                "Student registered successfully",
                response
        );
    }

    // Lấy danh sách học viên của lớp
    @GetMapping("/classes/{classId}/students")
    public ApiResponse<List<ClassStudentRes>> getStudentsByClass(
            @PathVariable Long classId
    ) {

        List<ClassStudentRes> students = classStudentService.getStudentsByClass(classId);

        return new ApiResponse<>(
                true,
                "Get students successfully",
                students
        );
    }

    // Lấy danh sách lớp của học viên
    @GetMapping("/students/{studentId}/classes")
    public ApiResponse<List<ClassStudentRes>> getClassesByStudent(
            @PathVariable Long studentId
    ) {

        List<ClassStudentRes> classes = classStudentService.getClassesByStudent(studentId);

        return new ApiResponse<>(
                true,
                "Get classes successfully",
                classes
        );
    }
    @PostMapping("/classes/{classId}/enroll")
    public ApiResponse<ClassStudentRes> enroll(
            @PathVariable Long classId,
            @RequestBody EnrollReq request) {
        ClassStudentRes Response = classStudentService.enroll(classId, request);
        return new ApiResponse<>(
                true,
                "Student registered successfully",
                Response
        );
    }
}
