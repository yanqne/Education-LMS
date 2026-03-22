package com.GiangTruong.LearningLMS.center.service.Course;

import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.CourseDTO;
import com.GiangTruong.LearningLMS.center.entity.Course;
import com.GiangTruong.LearningLMS.center.mapper.CourseMapper;
import com.GiangTruong.LearningLMS.center.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        return CourseMapper.toDTO(course);
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {

        Course course = CourseMapper.toEntity(courseDTO);

        Course savedCourse = courseRepository.save(course);

        return CourseMapper.toDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setFee(courseDTO.getFee());

        Course updatedCourse = courseRepository.save(course);

        return CourseMapper.toDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        courseRepository.deleteById(id);
    }
}
