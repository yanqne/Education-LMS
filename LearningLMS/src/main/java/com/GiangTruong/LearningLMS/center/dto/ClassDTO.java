package com.GiangTruong.LearningLMS.center.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClassDTO {

    private Long id;

    private String name;

    private Long courseId;

    private Long teacherId;

    private Long roomId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer maxStudents;
}