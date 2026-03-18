package com.GiangTruong.LearningLMS.center.dto.Attendance;

import com.GiangTruong.LearningLMS.center.Enum.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class AttendanceReq {
    private Long classId;
    private Long studentId;
    private LocalDate date;
    private Status status;
}
