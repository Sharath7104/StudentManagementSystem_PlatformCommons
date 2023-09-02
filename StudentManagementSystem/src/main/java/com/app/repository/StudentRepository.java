package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT a FROM Student a WHERE a.email = :username")
	Optional<Student> findByEmail(String username);
    // You can add custom query methods here if needed

	@Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Student> findByNameContainingIgnoreCase(String name);

	@Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
	List<Student> findByCoursesId(Long courseId);
	
	@Query("SELECT s FROM Student s JOIN FETCH s.addresses")
    List<Student> findAllWithAddresses();
}

