package com.GiangTruong.LearningLMS.center.service.Teacher;

import com.GiangTruong.LearningLMS.center.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> getAllTeachers();

    TeacherDTO getTeacherById(Long id);

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);

    void deleteTeacher(Long id);

}
