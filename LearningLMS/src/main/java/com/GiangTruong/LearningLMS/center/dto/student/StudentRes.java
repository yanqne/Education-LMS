package com.GiangTruong.LearningLMS.center.dto.student;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRes {

    private Long id;

    private String name;

    private String gender;

    private String phone;

    private String email;

    private String address;

    private String status;
}
