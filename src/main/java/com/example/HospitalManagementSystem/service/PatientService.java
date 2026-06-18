package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long patientId){
        return patientRepository.findById(patientId).
                orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
    }

    public void deleteById(Long patientId){
        if (!patientRepository.existsById(patientId))
            throw new EntityNotFoundException("Patient not found with id: " + patientId);
        patientRepository.deleteById(patientId);
        System.out.println("patient delete successfully" + patientId);
    }

    @Transactional
    public Patient addPatient(Patient patient){
     return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Page<Patient> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize));

    }

    public void updatePatientNameByID(Patient patient){
        Patient p = patientRepository.findById(patient.getId()).orElseThrow();
        p.setName(patient.getName());
        patientRepository.save(p);
        System.out.println("Patient updated successfully");
    }

}
