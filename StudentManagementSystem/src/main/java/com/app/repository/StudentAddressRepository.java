package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.StudentAddress;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {
    // You can add custom query methods here if needed
}

