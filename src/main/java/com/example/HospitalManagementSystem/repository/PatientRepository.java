package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.entity.types.BloodGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long > {
    Patient findByName(String name);

    Patient findByBirthDateOrEmail(LocalDate birthOfDate, String email);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Modifying
    @Query("update Patient p set p.name = :name where p.id = :id")
    Patient updateNameById(@Param("id") Long id, @Param("name") String name);

}
