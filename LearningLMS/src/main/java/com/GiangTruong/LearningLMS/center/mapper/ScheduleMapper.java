package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleRes;
import com.GiangTruong.LearningLMS.center.entity.Schedule;

public class ScheduleMapper {

    public static ScheduleRes toResponse(Schedule entity) {

        ScheduleRes response = new ScheduleRes();

        response.setId(entity.getId());

        response.setClassId(entity.getClassEntity().getId());
        response.setClassName(entity.getClassEntity().getName());

        response.setDayOfWeek(entity.getDayOfWeek());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());

        return response;
    }

}
