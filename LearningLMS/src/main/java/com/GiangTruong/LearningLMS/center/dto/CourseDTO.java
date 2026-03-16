package com.GiangTruong.LearningLMS.center.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseDTO {

    private Long id;

    private String name;

    private String description;

    private Integer duration;

    private BigDecimal fee;

}