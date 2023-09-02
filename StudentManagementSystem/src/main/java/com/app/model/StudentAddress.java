package com.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class StudentAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentaddressid")
    private Long id;

    @NotNull(message = "Address type is required")
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @NotBlank(message = "Area is required")
    private String area;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uniqueStudentCode") // Correctly specify the name of the foreign key column in the database
    @JsonIgnore
    private Student students;
    
    public enum AddressType {
        PERMANENT,
        CORRESPONDENCE,
        CURRENT
    }

}

