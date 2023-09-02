package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Admin;
import com.app.model.Student;

public interface AdminService {
    List<Admin> getAllAdmins();

    Optional<Admin> getAdminById(Long id);

    Admin saveAdmin(Admin admin);

    void deleteAdmin(Long id);
    
 // Assign courses to a student by student ID
    void assignCoursesToStudent(Long studentId, List<Long> courseIds);

    // Get students by name (search)
    List<Student> getStudentsByName(String name);

    // Get students assigned to a particular course by course ID
    List<Student> getStudentsByCourseId(Long courseId);

    // You can add additional service methods here if needed
}

