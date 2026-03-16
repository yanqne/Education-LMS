package com.GiangTruong.LearningLMS.center.dto.student;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentReq {

    @NotBlank
    private String name;

    private String gender;

    @Email
    private String email;

    @NotBlank
    private String phone;

    private String address;
}
