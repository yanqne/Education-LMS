package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.CourseDTO;
import com.GiangTruong.LearningLMS.center.entity.Course;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {

        CourseDTO dto = new CourseDTO();

        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        dto.setFee(course.getFee());

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {

        Course course = new Course();

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setFee(dto.getFee());

        return course;
    }

}