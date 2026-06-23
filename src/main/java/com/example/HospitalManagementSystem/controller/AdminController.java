package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.PatientResponseDTO;
import com.example.HospitalManagementSystem.entity.User;
import com.example.HospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder

    ) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        List<PatientResponseDTO> patients = patientService.getAllPatients(PageRequest.of(pageNumber - 1, pageSize, sort))
                .stream().map(patient -> modelMapper.map(patient, PatientResponseDTO.class)).toList();

        return ResponseEntity.ok(patients);
    }
}
