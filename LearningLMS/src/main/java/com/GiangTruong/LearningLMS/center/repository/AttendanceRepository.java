package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceRes;
import com.GiangTruong.LearningLMS.center.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByClassEntityId(Long classId);

    List<Attendance> findByDate(LocalDate date);

    boolean existsByClassEntityIdAndStudentIdAndDate(
            Long classId, Long studentId, LocalDate date
    );
    List<Attendance> findByClassEntityIdAndDate(Long classId, LocalDate date);
}