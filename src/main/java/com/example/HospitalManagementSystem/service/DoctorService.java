package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Doctor;
import com.example.HospitalManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor getDoctorById(Long doctorId){
        return doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
    }
    public void addDoctor(Doctor doctor){
        doctorRepository.save(doctor);

    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

}
