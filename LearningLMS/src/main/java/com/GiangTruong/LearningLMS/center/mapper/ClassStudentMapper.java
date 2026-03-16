package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.ClassStudentRes;
import com.GiangTruong.LearningLMS.center.entity.ClassStudent;

public class ClassStudentMapper {

    public static ClassStudentRes toResponse(ClassStudent entity) {

        ClassStudentRes response = new ClassStudentRes();

        response.setId(entity.getId());

        response.setClassId(entity.getClassEntity().getId());
        response.setClassName(entity.getClassEntity().getName());

        response.setStudentId(entity.getStudent().getId());
        response.setStudentName(entity.getStudent().getName());

        response.setJoinDate(entity.getJoinDate());

        return response;
    }

}
