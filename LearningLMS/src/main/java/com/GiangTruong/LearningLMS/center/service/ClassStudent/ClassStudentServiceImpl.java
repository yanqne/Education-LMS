package com.GiangTruong.LearningLMS.center.service.ClassStudent;

import com.GiangTruong.LearningLMS.center.dto.ClassStudent.ClassStudentReq;
import com.GiangTruong.LearningLMS.center.dto.ClassStudentRes;
import com.GiangTruong.LearningLMS.center.dto.EnrollReq;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.ClassStudent;
import com.GiangTruong.LearningLMS.center.entity.Schedule;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.mapper.ClassStudentMapper;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassStudentRepository;
import com.GiangTruong.LearningLMS.center.repository.ScheduleRepository;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.GiangTruong.LearningLMS.center.mapper.ClassStudentMapper.toResponse;

@Service
@RequiredArgsConstructor
public class ClassStudentServiceImpl implements ClassStudentService {

    private final ClassStudentRepository classStudentRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final ScheduleRepository scheduleRepository;

    private boolean isOverlapping(
            LocalTime start1, LocalTime end1,
            LocalTime start2, LocalTime end2
    ) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
    @Override
    public ClassStudentRes registerStudent(ClassStudentReq request) {

        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // check student already in class
        classStudentRepository
                .findByClassEntityIdAndStudentId(request.getClassId(), request.getStudentId())
                .ifPresent(cs -> {
                    throw new RuntimeException("Student already in class");
                });

        ClassStudent entity = new ClassStudent();

        entity.setClassEntity(classEntity);
        entity.setStudent(student);
        entity.setJoinDate(LocalDate.now());

        classStudentRepository.save(entity);

        return toResponse(entity);
    }

    @Override
    public List<ClassStudentRes> getStudentsByClass(Long classId) {

        return classStudentRepository
                .findByClassEntityId(classId)
                .stream()
                .map(ClassStudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassStudentRes> getClassesByStudent(Long studentId) {

        return classStudentRepository
                .findByStudentId(studentId)
                .stream()
                .map(ClassStudentMapper::toResponse)
                .collect(Collectors.toList());
    }
    public void checkScheduleConflict(Long studentId, Long classId) {

        List<Schedule> newSchedules = scheduleRepository.findByClassEntityId(classId);

        List<Schedule> currentSchedules = scheduleRepository.findSchedulesByStudentId(studentId);

        for (Schedule newSch : newSchedules) {
            for (Schedule curSch : currentSchedules) {

                // 1. Cùng ngày
                if (!newSch.getDayOfWeek().equals(curSch.getDayOfWeek())) {
                    continue;
                }

                // 2. Check overlap
                if (isOverlapping(
                        newSch.getStartTime(), newSch.getEndTime(),
                        curSch.getStartTime(), curSch.getEndTime()
                )) {
                    throw new RuntimeException("Schedule conflict detected");
                }
            }
        }
    }
    public ClassStudentRes enroll(Long classId, EnrollReq request) {

        ClassEntity clazz = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 1. Check đã đăng ký chưa
        boolean exists = classStudentRepository
                .existsByClassEntityIdAndStudentId(classId, student.getId());

        if (exists) {
            throw new RuntimeException("Student already enrolled");
        }

        // 2. Check full
        long count = classStudentRepository.countByClassEntityId(classId);

        if (count >= clazz.getMaxStudents()) {
            throw new RuntimeException("Class is full");
        }

        checkScheduleConflict(student.getId(), classId);
        // 3. Lưu
        ClassStudent entity = new ClassStudent();
        entity.setClassEntity(clazz);
        entity.setStudent(student);
        // trước khi save

        classStudentRepository.save(entity);

        return toResponse(entity);
    }
}