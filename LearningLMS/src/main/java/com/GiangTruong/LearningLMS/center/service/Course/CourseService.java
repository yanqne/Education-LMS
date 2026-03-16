package com.GiangTruong.LearningLMS.center.service.Course;
import com.GiangTruong.LearningLMS.center.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long id);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);

}
