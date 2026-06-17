package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.DoctorAppointmentResponseDTO;
import com.example.HospitalManagementSystem.dto.DoctorResponseDTO;
import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Doctor;
import com.example.HospitalManagementSystem.service.AppointmentService;
import com.example.HospitalManagementSystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final ModelMapper modelMapper;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping
    public List<DoctorResponseDTO> getAllDoctors(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorResponseDTO.class)).toList();
    }

    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO getDoctorById(@PathVariable Long id){
        return modelMapper.map(doctorService.getDoctorById(id), DoctorResponseDTO.class);
    }

    @GetMapping("/{doctorId}/appointments")
    public List<DoctorAppointmentResponseDTO> getDoctorAppointmentsById(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, DoctorAppointmentResponseDTO.class))
                .toList();

    }

}
