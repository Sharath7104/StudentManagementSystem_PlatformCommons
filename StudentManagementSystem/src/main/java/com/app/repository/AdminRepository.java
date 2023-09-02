package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("SELECT a FROM Admin a WHERE a.email = :username")
	Optional<Admin> findByEmail(String username);
    // You can add custom query methods here if needed
}

