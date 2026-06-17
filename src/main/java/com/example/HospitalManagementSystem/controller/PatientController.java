package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.PatientAppointmentResponseDTO;
import com.example.HospitalManagementSystem.dto.PatientResponseDTO;
import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.repository.AppointmentRepository;
import com.example.HospitalManagementSystem.service.AppointmentService;
import com.example.HospitalManagementSystem.service.PatientService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    public List<PatientResponseDTO> getAllPatient(){
        List<Patient> patientList = patientService.getAllPatient();
        return patientList.stream().map(patient -> modelMapper.map(patient, PatientResponseDTO.class)).toList();
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient){
    patientService.addPatient(patient);

    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        patientService.deleteById(patientId);
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
