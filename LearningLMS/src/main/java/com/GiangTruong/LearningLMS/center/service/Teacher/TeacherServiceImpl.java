package com.GiangTruong.LearningLMS.center.service.Teacher;

import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.TeacherDTO;
import com.GiangTruong.LearningLMS.center.entity.Teacher;
import com.GiangTruong.LearningLMS.center.mapper.TeacherMapper;
import com.GiangTruong.LearningLMS.center.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {

        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));

        return TeacherMapper.toDTO(teacher);
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        Teacher teacher = TeacherMapper.toEntity(teacherDTO);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherMapper.toDTO(savedTeacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));

        teacher.setName(teacherDTO.getName());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSpecialty(teacherDTO.getSpecialty());
        teacher.setSalary(teacherDTO.getSalary());

        Teacher updatedTeacher = teacherRepository.save(teacher);

        return TeacherMapper.toDTO(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {

        teacherRepository.deleteById(id);
    }
}
