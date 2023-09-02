package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Student;

public interface StudentService {
    List<Student> getAllStudents();

    Optional<Student> getStudentById(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);

    // You can add additional service methods here if needed
}

