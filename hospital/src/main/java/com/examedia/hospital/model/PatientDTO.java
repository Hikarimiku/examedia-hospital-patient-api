package com.examedia.hospital.model;

import com.examedia.hospital.enums.PatientBloodType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientDTO {
    private Long id;

    @NotNull(message = "First Name is Required")
    private String firstName;

    @NotNull(message = "Last Name is Required")
    private String lastName;

    @NotNull(message = "Date of Birth is Required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Phone Number is Required")
    private String phoneNumber;

    private String email;

    private String address;

    @NotNull(message = "Emergency Contact is Required")
    private String emergencyContact;

    private Boolean isActive;

    private PatientBloodType bloodType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
