package com.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.StudentException;
import com.app.model.Admin;
import com.app.model.Course;
import com.app.model.Student;
import com.app.repository.AdminRepository;
import com.app.repository.CourseRepository;
import com.app.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

	private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
    	admin.setRole("ROLE_ADMIN");
    	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    
    @Override
    public void assignCoursesToStudent(Long studentId, List<Long> courseIds) {
        // Retrieve the student by ID
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new StudentException("Student with ID " + studentId + " not found");
        }

        // Retrieve the courses by IDs
        List<Course> courses = courseRepository.findAllById(courseIds);

        // Assign the courses to the student
        student.getCourses().addAll(courses);
        studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByCoursesId(courseId);
    }

    // You can add additional service methods here if needed
}
