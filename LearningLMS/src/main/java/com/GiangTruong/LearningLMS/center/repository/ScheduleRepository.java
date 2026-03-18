package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByClassEntityId(Long classId);

    @Query("""
    SELECT s FROM Schedule s
    JOIN s.classEntity c
    JOIN ClassStudent cs ON cs.classEntity.id = c.id
    WHERE cs.student.id = :studentId
""")
    List<Schedule> findSchedulesByStudentId(Long studentId);
}