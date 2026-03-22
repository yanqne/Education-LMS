package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementReq;
import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementRes;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Announcement.AnnouncementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@Tag(name = "announcement API", description = "APIs for managing announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ApiResponse<AnnouncementRes> create(
            @RequestBody AnnouncementReq req
    ) {
        return new ApiResponse<>(
                true,
                "Announcement created successfully",
                announcementService.create(req)
        );
    }

    @GetMapping
    public ApiResponse<List<AnnouncementRes>> getAll() {
        return new ApiResponse<>(
                true,
                "Announcements retrieved successfully",
                announcementService.getAll()
        );
    }

    @GetMapping("/class/{classId}")
    public ApiResponse<List<AnnouncementRes>> getByClass(
            @PathVariable Long classId
    ) {
        return new ApiResponse<>(
                true,
                "Class announcements retrieved successfully",
                announcementService.getByClass(classId)
        );
    }

    @GetMapping("/global")
    public ApiResponse<List<AnnouncementRes>> getGlobal() {
        return new ApiResponse<>(
                true,
                "Global announcements retrieved successfully",
                announcementService.getGlobal()
        );
    }
}
