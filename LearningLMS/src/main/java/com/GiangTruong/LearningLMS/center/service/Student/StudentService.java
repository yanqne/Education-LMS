package com.GiangTruong.LearningLMS.center.service.Student;

import com.GiangTruong.LearningLMS.center.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);

    StudentDTO getCurrentStudent();

    StudentDTO createProfiles(StudentDTO dto);
}
