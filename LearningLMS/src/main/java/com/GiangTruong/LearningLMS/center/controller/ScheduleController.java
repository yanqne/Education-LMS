package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleReq;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleRes;
import com.GiangTruong.LearningLMS.center.service.Schedule.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Schedule API", description = "APIs for managing Schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/classes/{classId}/schedules")
    public ApiResponse<ScheduleRes> createSchedule(
            @PathVariable Long classId,
            @RequestBody ScheduleReq request
    ) {

        request.setClassId(classId);
        ScheduleRes response = scheduleService.createSchedule(request);
        return new ApiResponse<>(
                true,
                "Schedule created successfully",
                response
        );
    }

    @GetMapping("/classes/{classId}/schedules")
    public ApiResponse<List<ScheduleRes>> getSchedulesByClass(
            @PathVariable Long classId
    ) {

        List<ScheduleRes> schedules = scheduleService.getSchedulesByClass(classId);

        return new ApiResponse<>(
                true,
                "Get schedules successfully",
                schedules
        );
    }

    @DeleteMapping("/schedules/{id}")
    public ApiResponse<String> deleteSchedule(@PathVariable Long id) {

        scheduleService.deleteSchedule(id);
        return new ApiResponse<>(
                true,
                "Schedule deleted successfully",
                "Deleted"
        );
    }
}
