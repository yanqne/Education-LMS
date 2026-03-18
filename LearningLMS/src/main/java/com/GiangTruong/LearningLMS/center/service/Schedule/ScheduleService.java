package com.GiangTruong.LearningLMS.center.service.Schedule;

import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleReq;
import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleRes;

import java.util.List;

public interface ScheduleService {
    ScheduleRes createSchedule(ScheduleReq req);

    List<ScheduleRes> getSchedulesByClass(Long classId);

    void deleteSchedule(Long id);

}
