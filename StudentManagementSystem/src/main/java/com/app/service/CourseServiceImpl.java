package com.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Course;
import com.app.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    
    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        
        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            // Update the fields of the existing course with the values from updatedCourse
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setCourseType(updatedCourse.getCourseType());
            existingCourse.setDuration(updatedCourse.getDuration());
            // Save the updated course to the repository
            return courseRepository.save(existingCourse);
        }
        
        return null; // Course with the given ID not found
    }

    // You can add additional service methods here if needed
}
