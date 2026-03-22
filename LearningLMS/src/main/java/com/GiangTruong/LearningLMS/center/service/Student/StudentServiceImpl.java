package com.GiangTruong.LearningLMS.center.service.Student;

import com.GiangTruong.LearningLMS.center.config.BadRequestException;
import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.StudentDTO;
import com.GiangTruong.LearningLMS.center.entity.User;
import com.GiangTruong.LearningLMS.center.mapper.StudentMapper;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import com.GiangTruong.LearningLMS.center.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        // 🔥 LẤY USER HIỆN TẠI
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        System.out.println("USERNAME FROM TOKEN: " + username);
        // 🔥 LẤY USER TỪ DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        String role = user.getRole().getName();

        // 👑 ADMIN
        if (role.equals("ADMIN")) {
            System.out.println("Đăng nhập bằng admin");
            return studentRepository.findAll()
                    .stream()
                    .map(StudentMapper::toDTO)
                    .toList();
        }

        // 👨‍🎓 STUDENT
        if (role.equals("STUDENT")) {
            System.out.println("Đăng nhập bằng student");
            if(!studentRepository.existsByUserId(user.getId())){
                throw new BadRequestException("Please complete profile first");
            }
            return studentRepository.findByUserId(user.getId())
                    .stream()
                    .map(StudentMapper::toDTO)
                    .toList();
        }

        return List.of();
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {

        Student student = StudentMapper.toEntity(studentDTO);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, @NonNull StudentDTO studentDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

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

    @Override
    public StudentDTO getCurrentStudent() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return studentRepository.findByUserId(user.getId())
                .map(StudentMapper::toDTO)
                .orElseThrow(() -> new BadRequestException("PROFILE_NOT_COMPLETE"));
    }
    @Override
    public StudentDTO createProfiles(StudentDTO dto){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        //Check valid
        if(studentRepository.existsByUserId(user.getId())){
            throw new BadRequestException("Profile already exists");
        }
        Student student = StudentMapper.toEntity(dto);
        student.setUser(user);
        return StudentMapper.toDTO(studentRepository.save(student));
    }
}