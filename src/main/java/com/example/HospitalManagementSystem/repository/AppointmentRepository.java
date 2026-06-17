package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient WHERE a.doctor.id = :doctorId")
    List<Appointment> getAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient WHERE a.patient.id = :patientId")
    List<Appointment> getAppointmentsByPatientId(@Param("patientId") Long patientId);

}