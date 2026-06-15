package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/view")
    public List<Patient> getAllPatient(){
        return patientService.getAllPatient();
    }

    @PostMapping("/add")
    public void addPatient(@RequestBody Patient patient){
    patientService.addPatient(patient);

    }

    @DeleteMapping("/delete/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
    }


    @PutMapping("/update/{patientId}")
    public void updatePatient(@RequestBody Patient patient, @PathVariable Long patientId){
        patient.setId(patientId);
        patientService.updatePatientNameByID(patient);
    }
}
