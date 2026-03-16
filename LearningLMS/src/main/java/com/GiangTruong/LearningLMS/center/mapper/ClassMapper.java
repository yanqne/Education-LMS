package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.ClassDTO;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;

public class ClassMapper {

    public static ClassDTO toDTO(ClassEntity entity) {

        ClassDTO dto = new ClassDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());

        dto.setCourseId(entity.getCourse().getId());
        dto.setTeacherId(entity.getTeacher().getId());
        dto.setRoomId(entity.getRoom().getId());

        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setMaxStudents(entity.getMaxStudents());

        return dto;
    }
}
