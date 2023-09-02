package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // You can add custom query methods here if needed
}

