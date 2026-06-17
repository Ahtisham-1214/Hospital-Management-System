package com.example.HospitalManagementSystem.dto;

import com.example.HospitalManagementSystem.entity.types.BloodGroupType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PatientResponseDTO {
    private Long id;
    private String name;
    private String gender;
    private LocalDateTime createdAt;
    private String email;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
