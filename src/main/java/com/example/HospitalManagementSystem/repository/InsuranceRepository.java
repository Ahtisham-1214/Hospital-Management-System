package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
