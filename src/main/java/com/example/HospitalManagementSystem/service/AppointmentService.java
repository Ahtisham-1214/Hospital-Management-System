package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Doctor;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.repository.AppointmentRepository;
import com.example.HospitalManagementSystem.repository.DoctorRepository;
import com.example.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Appointment addAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update, because it is dirty

        doctor.getAppointments().add(appointment); // just for bidirectional consistency

        return appointment;
    }

    @Transactional
    public Appointment cancelAppointment(Long appointmentId, Long patientId, Long doctorId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();

        appointmentRepository.delete(appointment);
        patient.getAppointments().remove(appointment);
        doctor.getAppointments().remove(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId){
      return appointmentRepository.getAppointmentsByDoctorId(doctorId);
    }


    public List<Appointment> getAppointmentsByPatientId(Long patientId){
        return appointmentRepository.getAppointmentsByPatientId(patientId);
    }
}
