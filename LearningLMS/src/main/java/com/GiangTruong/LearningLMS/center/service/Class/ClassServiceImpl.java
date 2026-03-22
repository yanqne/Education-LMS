package com.GiangTruong.LearningLMS.center.service.Class;

import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.ClassDTO;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.Course;
import com.GiangTruong.LearningLMS.center.entity.Room;
import com.GiangTruong.LearningLMS.center.entity.Teacher;
import com.GiangTruong.LearningLMS.center.mapper.ClassMapper;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.CourseRepository;
import com.GiangTruong.LearningLMS.center.repository.RoomRepository;
import com.GiangTruong.LearningLMS.center.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;

    public ClassServiceImpl(
            ClassRepository classRepository,
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            RoomRepository roomRepository) {

        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(ClassMapper::toDTO)
                .toList();
    }

    @Override
    public ClassDTO getClassById(Long id) {

        ClassEntity entity = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Class not found"));

        return ClassMapper.toDTO(entity);
    }

    @Override
    public List<ClassDTO> getClassesByCourse(Long courseId) {

        return classRepository.findByCourseId(courseId)
                .stream()
                .map(ClassMapper::toDTO)
                .toList();
    }

    @Override
    public ClassDTO createClass(ClassDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found"));

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Teacher not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new NotFoundException("Room not found"));

        ClassEntity entity = new ClassEntity();

        entity.setName(dto.getName());
        entity.setCourse(course);
        entity.setTeacher(teacher);
        entity.setRoom(room);
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setMaxStudents(dto.getMaxStudents());

        entity.setCreatedAt(LocalDateTime.now());

        ClassEntity saved = classRepository.save(entity);

        return ClassMapper.toDTO(saved);
    }

    @Override
    public ClassDTO updateClass(Long id, ClassDTO dto) {

        ClassEntity entity = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Class not found"));

        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setMaxStudents(dto.getMaxStudents());

        entity.setUpdatedAt(LocalDateTime.now());

        ClassEntity updated = classRepository.save(entity);

        return ClassMapper.toDTO(updated);
    }

    @Override
    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}
