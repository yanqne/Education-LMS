package com.GiangTruong.LearningLMS.center.mapper;


import com.GiangTruong.LearningLMS.center.dto.StudentDTO;
import com.GiangTruong.LearningLMS.center.entity.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setGender(student.getGender());
        dto.setBirthDate(student.getBirthDate());
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        dto.setAddress(student.getAddress());
        dto.setStatus(student.getStatus());

        return dto;
    }

    public static Student toEntity(StudentDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setGender(dto.getGender());
        student.setBirthDate(dto.getBirthDate());
        student.setPhone(dto.getPhone());
        student.setEmail(dto.getEmail());
        student.setAddress(dto.getAddress());
        student.setStatus(dto.getStatus());

        return student;
    }
}
