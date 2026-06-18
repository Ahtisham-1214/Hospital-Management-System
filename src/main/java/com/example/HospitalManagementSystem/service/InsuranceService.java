package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Insurance;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.repository.InsuranceRepository;
import com.example.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, long patientID){
        Patient patient = patientRepository.findById(patientID).orElseThrow();

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional relationship
        return patient;
    }

    @Transactional
    public Patient cancelInsurance(Long patientID){
        Patient patient = patientRepository.findById(patientID).
                orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientID));
        patient.setInsurance(null);
        return patient;
    }
}
