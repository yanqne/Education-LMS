package com.GiangTruong.LearningLMS.center.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Long id;

    private String name;

    private String gender;

    private LocalDate birthDate;

    private String phone;

    private String email;

    private String address;

    private String status;

}
