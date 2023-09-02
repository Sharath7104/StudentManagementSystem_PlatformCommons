package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Admin;
import com.app.model.Student;
import com.app.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Long adminId) {
        Admin admin = adminService.getAdminById(adminId)
                .orElse(null);

        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") Long adminId, @RequestBody Admin admin) {
        if (!adminId.equals(admin.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Admin updatedAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("adminId") Long adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/assigncourses/{studentId}")
    public ResponseEntity<Void> assignCoursesToStudent(
            @PathVariable Long studentId, @RequestBody List<Long> courseIds) {
        adminService.assignCoursesToStudent(studentId, courseIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get students by name (search)
    @GetMapping("/studentsbyname")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name) {
        List<Student> students = adminService.getStudentsByName(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Get students assigned to a particular course by course ID
    @GetMapping("/studentsbycourse/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long courseId) {
        List<Student> students = adminService.getStudentsByCourseId(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}

