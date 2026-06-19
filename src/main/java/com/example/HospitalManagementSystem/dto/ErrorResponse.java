package com.example.HospitalManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}