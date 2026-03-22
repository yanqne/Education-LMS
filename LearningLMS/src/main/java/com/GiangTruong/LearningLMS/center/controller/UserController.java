package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.User.UserReq;
import com.GiangTruong.LearningLMS.center.dto.User.UserRes;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.User.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "user API", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserRes> create(@RequestBody UserReq req) {
        return new ApiResponse<>(
                true,
                "User created successfully",
                userService.create(req)
        );
    }

    @GetMapping
    public ApiResponse<List<UserRes>> getAll() {
        return new ApiResponse<>(
                true,
                "Users retrieved successfully",
                userService.getAll()
        );
    }
}
