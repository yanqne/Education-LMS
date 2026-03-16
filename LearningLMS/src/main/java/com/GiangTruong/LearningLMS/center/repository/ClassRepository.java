package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    List<ClassEntity> findByCourseId(Long courseId);

    List<ClassEntity> findByTeacherId(Long teacherId);

}
