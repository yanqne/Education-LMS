package com.GiangTruong.LearningLMS.center.dto.Schedule;

import com.GiangTruong.LearningLMS.center.Enum.DayOfWeek;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleRes {

    private Long id;

    private Long classId;

    private String className;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

}
