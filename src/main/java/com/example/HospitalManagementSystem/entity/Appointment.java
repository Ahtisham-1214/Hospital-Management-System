package com.example.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false, name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Doctor doctor;
}

