package com.GiangTruong.LearningLMS.center.service.ClassStudent;

import com.GiangTruong.LearningLMS.center.dto.ClassStudentReq;
import com.GiangTruong.LearningLMS.center.dto.ClassStudentRes;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.ClassStudent;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.mapper.ClassStudentMapper;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassStudentRepository;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassStudentServiceImpl implements ClassStudentService {

    private final ClassStudentRepository classStudentRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

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

        return ClassStudentMapper.toResponse(entity);
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
}