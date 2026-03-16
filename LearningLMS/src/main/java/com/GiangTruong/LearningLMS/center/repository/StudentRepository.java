package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByStatus(String status);

}