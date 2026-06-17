package com.example.HospitalManagementSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DoctorAppointmentResponseDTO {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;

    private Long patientId;
    private String patientName;
    private String patientGender;
}
