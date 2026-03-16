package com.GiangTruong.LearningLMS.center.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassStudentRes {

    private Long id;

    private Long classId;

    private String className;

    private Long studentId;

    private String studentName;

    private LocalDate joinDate;
}
