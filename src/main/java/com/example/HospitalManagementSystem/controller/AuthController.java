package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.LoginRequestDTO;
import com.example.HospitalManagementSystem.dto.LoginResponseDTO;
import com.example.HospitalManagementSystem.dto.SignUpResponseDTO;
import com.example.HospitalManagementSystem.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signup(@RequestBody LoginRequestDTO signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
