package com.example.HospitalManagementSystem.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class DoctorResponseDTO{
       private Long id;
       private String email;
       private String name;
       private String specialization;
 }
