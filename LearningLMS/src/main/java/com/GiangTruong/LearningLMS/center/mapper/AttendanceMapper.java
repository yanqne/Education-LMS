package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceReq;
import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceRes;
import com.GiangTruong.LearningLMS.center.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public Attendance toEntity(AttendanceReq req) {
        Attendance entity = new Attendance();
        entity.setDate(req.getDate());
        entity.setStatus(req.getStatus());
        return entity;
    }

    public AttendanceRes toResponse(Attendance entity) {
        AttendanceRes res = new AttendanceRes();
        res.setId(entity.getId());
        res.setClassId(entity.getClassEntity().getId());
        res.setStudentId(entity.getStudent().getId());
        res.setDate(entity.getDate());
        res.setStatus(entity.getStatus());
        return res;
    }
}
