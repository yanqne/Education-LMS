package com.GiangTruong.LearningLMS.center.service.Attendance;

import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceReq;
import com.GiangTruong.LearningLMS.center.dto.Attendance.AttendanceRes;
import com.GiangTruong.LearningLMS.center.dto.Attendance.BulkAttendanceReq;
import com.GiangTruong.LearningLMS.center.entity.Attendance;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.mapper.AttendanceMapper;
import com.GiangTruong.LearningLMS.center.repository.AttendanceRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassStudentRepository;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final ClassStudentRepository classStudentRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceRes create(AttendanceReq req) {

        ClassEntity classEntity = classRepository.findById(req.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Student student = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        boolean enrolled = classStudentRepository
                .existsByClassEntityIdAndStudentId(req.getClassId(), req.getStudentId());

        if (!enrolled) {
            throw new RuntimeException("Student not enrolled in class");
        }

        boolean exists = attendanceRepository
                .existsByClassEntityIdAndStudentIdAndDate(
                        req.getClassId(), req.getStudentId(), req.getDate()
                );

        if (exists) {
            throw new RuntimeException("Attendance already taken");
        }

        Attendance entity = attendanceMapper.toEntity(req);
        entity.setClassEntity(classEntity);
        entity.setStudent(student);

        return attendanceMapper.toResponse(attendanceRepository.save(entity));
    }

    @Override
    public List<AttendanceRes> bulkCreate(BulkAttendanceReq req) {

        ClassEntity classEntity = classRepository.findById(req.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        List<AttendanceRes> responses = new ArrayList<>();

        for (BulkAttendanceReq.StudentAttendance item : req.getAttendances()) {

            Student student = studentRepository.findById(item.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            boolean enrolled = classStudentRepository
                    .existsByClassEntityIdAndStudentId(req.getClassId(), item.getStudentId());

            if (!enrolled) continue;

            boolean exists = attendanceRepository
                    .existsByClassEntityIdAndStudentIdAndDate(
                            req.getClassId(), item.getStudentId(), req.getDate()
                    );

            if (exists) continue;

            Attendance entity = new Attendance();
            entity.setClassEntity(classEntity);
            entity.setStudent(student);
            entity.setDate(req.getDate());
            entity.setStatus(item.getStatus());

            responses.add(
                    attendanceMapper.toResponse(attendanceRepository.save(entity))
            );
        }

        return responses;
    }

    @Override
    public List<AttendanceRes> getByClassAndDate(Long classId, LocalDate date) {
        return attendanceRepository.findByClassEntityIdAndDate(classId, date)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }
}
