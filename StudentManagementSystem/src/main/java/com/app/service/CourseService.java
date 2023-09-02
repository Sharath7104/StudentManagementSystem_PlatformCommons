package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Course;

public interface CourseService {
    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Long id);

	Course updateCourse(Long id, Course updatedCourse);

    // You can add additional service methods here if needed
}
