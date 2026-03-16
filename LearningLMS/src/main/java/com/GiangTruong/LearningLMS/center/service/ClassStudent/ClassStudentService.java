package com.GiangTruong.LearningLMS.center.service.ClassStudent;

import com.GiangTruong.LearningLMS.center.dto.ClassStudentReq;
import com.GiangTruong.LearningLMS.center.dto.ClassStudentRes;

import java.util.List;

public interface ClassStudentService {
    ClassStudentRes registerStudent(ClassStudentReq request);

    List<ClassStudentRes> getStudentsByClass(Long classId);

    List<ClassStudentRes> getClassesByStudent(Long studentId);
}
