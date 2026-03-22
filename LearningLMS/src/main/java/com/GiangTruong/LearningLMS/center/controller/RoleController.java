package com.GiangTruong.LearningLMS.center.controller;

import com.GiangTruong.LearningLMS.center.dto.Role.RoleReq;
import com.GiangTruong.LearningLMS.center.dto.Role.RoleRes;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Role.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Tag(name = "role API", description = "APIs for managing roles")
public class RoleController {

    private final RoleService roleSerivce;

    @PostMapping
    public ApiResponse<RoleRes> create(@RequestBody RoleReq req){
        return new ApiResponse<>(
                true,
                "Role created successfully",
                roleSerivce.create(req)
        );
    }
    @GetMapping
    public ApiResponse<List<RoleRes>> getAll(){
        return new ApiResponse<>(
                true,
                "Role retrieved successfully",
                roleSerivce.getAll()
        );
    }
}
