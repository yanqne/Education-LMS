package com.GiangTruong.LearningLMS.center.service.Attendance;

import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceReq;
import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceRes;
import com.GiangTruong.LearningLMS.center.dto.Attendance.BulkAttendanceReq;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceRes create(AttendanceReq req);

    List<AttendanceRes> bulkCreate(BulkAttendanceReq Bulkreq);

    List<AttendanceRes> getByClassAndDate(Long classId, LocalDate date);

}
