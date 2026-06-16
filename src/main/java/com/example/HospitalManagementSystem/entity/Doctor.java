package com.example.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String specialization;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();
}
