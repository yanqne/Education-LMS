package com.GiangTruong.LearningLMS.center.service.Student;

import com.GiangTruong.LearningLMS.center.dto.StudentDTO;
import com.GiangTruong.LearningLMS.center.mapper.StudentMapper;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {

        Student student = StudentMapper.toEntity(studentDTO);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setPhone(studentDTO.getPhone());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setStatus(studentDTO.getStatus());

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}