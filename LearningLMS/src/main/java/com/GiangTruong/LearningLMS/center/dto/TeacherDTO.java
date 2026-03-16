package com.GiangTruong.LearningLMS.center.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TeacherDTO {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String specialty;

    private BigDecimal salary;

}