package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassStudentRepository extends JpaRepository<ClassStudent, Long> {

    List<ClassStudent> findByClassEntityId(Long classId);

    List<ClassStudent> findByStudentId(Long studentId);

}