package com.GiangTruong.LearningLMS.center.service.Class;

import com.GiangTruong.LearningLMS.center.dto.ClassDTO;

import java.util.List;

public interface ClassService {

    List<ClassDTO> getAllClasses();

    ClassDTO getClassById(Long id);

    List<ClassDTO> getClassesByCourse(Long courseId);

    ClassDTO createClass(ClassDTO dto);

    ClassDTO updateClass(Long id, ClassDTO dto);

    void deleteClass(Long id);
}
