package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.StudentAddress;

public interface StudentAddressService {
    List<StudentAddress> getAllStudentAddresses();

    Optional<StudentAddress> getStudentAddressById(Long id);

    public StudentAddress saveStudentAddress(StudentAddress address, Long studentId);

    void deleteStudentAddress(Long id);

    // You can add additional service methods here if needed
}

