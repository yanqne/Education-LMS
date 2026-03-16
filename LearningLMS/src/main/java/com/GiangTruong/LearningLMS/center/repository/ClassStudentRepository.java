package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassStudentRepository extends JpaRepository<ClassStudent, Long> {

    List<ClassStudent> findByClassEntityId(Long classId);

    List<ClassStudent> findByStudentId(Long studentId);

    Optional<ClassStudent> findByClassEntityIdAndStudentId(Long classId, Long studentId);

}