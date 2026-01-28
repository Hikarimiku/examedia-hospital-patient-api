package com.examedia.hospital.entity;


import com.examedia.hospital.enums.PatientBloodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String phoneNumber;

    private String email;

    private String address;

    @NotNull
    private String emergencyContact;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private PatientBloodType bloodType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
