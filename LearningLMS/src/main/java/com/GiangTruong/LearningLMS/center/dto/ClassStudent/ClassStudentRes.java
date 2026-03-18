package com.GiangTruong.LearningLMS.center.dto.ClassStudent;

import lombok.Data;

@Data
public class ClassStudentRes {
    private Long id;
    private Long classId;
    private String className;
    private Long studentId;
    private String studentName;
}
