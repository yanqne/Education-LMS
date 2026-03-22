package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Login.LoginReq;
import com.GiangTruong.LearningLMS.center.dto.Login.LoginRes;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginRes> login(@RequestBody LoginReq req) {
        return new ApiResponse<>(
                true,
                "Login successful",
                authService.login(req)
        );
    }
}
