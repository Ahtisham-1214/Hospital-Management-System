package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.PatientAppointmentResponseDTO;
import com.example.HospitalManagementSystem.dto.PatientResponseDTO;
import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.service.AppointmentService;
import com.example.HospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;


    @GetMapping
    public List<PatientResponseDTO> getAllPatients(){
        List<Patient> patientList = patientService.getAllPatients();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientResponseDTO.class)).toList();
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(patientService.addPatient(patient), PatientResponseDTO.class));

    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{patientId}")
    public void updatePatient(@RequestBody Patient patient, @PathVariable Long patientId){
        patient.setId(patientId);
        patientService.updatePatientNameByID(patient);
    }

    @GetMapping("/{patientId}/appointments")
    public List<PatientAppointmentResponseDTO> getPatientAppointmentById(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return appointments.stream().
                map(appointment -> modelMapper.map(appointment, PatientAppointmentResponseDTO.class))
                .toList();
    }
}
