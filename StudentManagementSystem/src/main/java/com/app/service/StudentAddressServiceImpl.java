package com.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.StudentException;
import com.app.model.Student;
import com.app.model.StudentAddress;
import com.app.repository.StudentAddressRepository;
import com.app.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAddressServiceImpl implements StudentAddressService {

    private final StudentAddressRepository studentAddressRepository;
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentAddressServiceImpl(StudentAddressRepository studentAddressRepository, StudentRepository studentRepository) {
        this.studentAddressRepository = studentAddressRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentAddress> getAllStudentAddresses() {
        return studentAddressRepository.findAll();
    }

    @Override
    public Optional<StudentAddress> getStudentAddressById(Long id) {
        return studentAddressRepository.findById(id);
    }

    
    @Override
    public StudentAddress saveStudentAddress(StudentAddress address, Long studentId) {
        // Get the corresponding Student entity by ID
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            address.setStudents(student); // Associate the StudentAddress with the Student
            return studentAddressRepository.save(address);
        } else {
            // Handle the case where the student doesn't exist
            throw new StudentException("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public void deleteStudentAddress(Long id) {
        studentAddressRepository.deleteById(id);
    }

    // You can add additional service methods here if needed
}
