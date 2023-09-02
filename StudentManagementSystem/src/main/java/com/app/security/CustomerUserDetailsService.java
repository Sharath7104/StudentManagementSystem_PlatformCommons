package com.app.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Admin;
import com.app.model.Student;
import com.app.repository.AdminRepository;
import com.app.repository.StudentRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository; // Repository for Student entity

    @Autowired
    private AdminRepository adminRepository; // Repository for Admin entity

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> studentOpt = studentRepository.findByEmail(username);
        Optional<Admin> adminOpt = adminRepository.findByEmail(username);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // Load student details and authorities
            return buildUserDetails(student.getEmail(), student.getPassword(), student.getRole());
        } else if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Load admin details and authorities
            return buildUserDetails(admin.getEmail(), admin.getPassword(), admin.getRole());
        } else {
            throw new UsernameNotFoundException("User Details not found with this username: " + username);
        }
    }

    private UserDetails buildUserDetails(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
        authorities.add(sga);
        return new User(username, password, authorities);
    }
}
