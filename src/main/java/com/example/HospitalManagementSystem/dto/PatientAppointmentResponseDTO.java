package com.example.HospitalManagementSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientAppointmentResponseDTO {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;

    private Long doctorId;
    private String doctorName;
    private String doctorSpecialization;
}
