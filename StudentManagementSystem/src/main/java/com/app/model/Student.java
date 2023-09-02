package com.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Student extends User{      

    @Column(name = "unique_student_code", unique = true, nullable = false)
    @NotBlank(message = "Unique student code is required")
    private String uniqueStudentCode;

    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StudentAddress> addresses;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;
}

