package com.GiangTruong.LearningLMS.center.service.Schedule;

import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleReq;
import com.GiangTruong.LearningLMS.center.dto.Schedule.ScheduleRes;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.Schedule;
import com.GiangTruong.LearningLMS.center.mapper.ScheduleMapper;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ClassRepository classRepository;

    @Override
    public ScheduleRes createSchedule(ScheduleReq request) {

        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Schedule schedule = new Schedule();

        schedule.setClassEntity(classEntity);
        schedule.setDayOfWeek(request.getDayOfWeek());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());

        scheduleRepository.save(schedule);

        return ScheduleMapper.toResponse(schedule);
    }

    @Override
    public List<ScheduleRes> getSchedulesByClass(Long classId) {

        return scheduleRepository.findByClassEntityId(classId)
                .stream()
                .map(ScheduleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
