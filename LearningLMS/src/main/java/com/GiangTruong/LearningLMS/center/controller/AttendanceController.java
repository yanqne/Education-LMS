package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceReq;
import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceRes;
import com.GiangTruong.LearningLMS.center.dto.Attendance.BulkAttendanceReq;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ApiResponse<AttendanceRes> create(@RequestBody AttendanceReq req) {
        return new ApiResponse<>(
                true,
                "Attendance created successfully",
                attendanceService.create(req)
        );
    }

    @PostMapping("/bulk")
    public ApiResponse<List<AttendanceRes>> bulkCreate(
            @RequestBody BulkAttendanceReq req
    ) {
        return new ApiResponse<>(
                true,
                "Bulk attendance created successfully",
                attendanceService.bulkCreate(req)
        );
    }

    @GetMapping("/class/{classId}")
    public ApiResponse<List<AttendanceRes>> getByClassAndDate(
            @PathVariable Long classId,
            @RequestParam LocalDate date
    ) {
        return new ApiResponse<>(
                true,
                "Attendance retrieved successfully",
                attendanceService.getByClassAndDate(classId, date)
        );
    }
}
