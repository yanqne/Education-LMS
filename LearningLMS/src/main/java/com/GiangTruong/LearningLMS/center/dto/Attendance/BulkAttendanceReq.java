package com.GiangTruong.LearningLMS.center.dto.Attendance;

import com.GiangTruong.LearningLMS.center.Enum.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Data
@Getter
@Setter
public class BulkAttendanceReq {
    private Long classId;
    private LocalDate date;
    private List<StudentAttendance> attendances;

    @Getter
    @Setter
    public static class StudentAttendance{
        private Long studentId;
        private Status status;

    }
}
