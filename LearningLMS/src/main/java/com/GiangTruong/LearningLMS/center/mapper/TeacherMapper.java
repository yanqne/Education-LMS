package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.TeacherDTO;
import com.GiangTruong.LearningLMS.center.entity.Teacher;

public class TeacherMapper {

    public static TeacherDTO toDTO(Teacher teacher) {

        TeacherDTO dto = new TeacherDTO();

        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setPhone(teacher.getPhone());
        dto.setEmail(teacher.getEmail());
        dto.setSpecialty(teacher.getSpecialty());
        dto.setSalary(teacher.getSalary());

        return dto;
    }

    public static Teacher toEntity(TeacherDTO dto) {

        Teacher teacher = new Teacher();

        teacher.setName(dto.getName());
        teacher.setPhone(dto.getPhone());
        teacher.setEmail(dto.getEmail());
        teacher.setSpecialty(dto.getSpecialty());
        teacher.setSalary(dto.getSalary());

        return teacher;
    }
}
