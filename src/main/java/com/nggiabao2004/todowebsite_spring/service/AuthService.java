package com.nggiabao2004.todowebsite_spring.service;

import com.nggiabao2004.todowebsite_spring.dto.AuthRequestDto;
import com.nggiabao2004.todowebsite_spring.dto.AuthResponseDto;
import com.nggiabao2004.todowebsite_spring.dto.RegisterRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponseDto> login(AuthRequestDto loginRequest, HttpServletRequest request);
    ResponseEntity<String> register(RegisterRequestDto registerRequest);
    ResponseEntity<String> logout(HttpServletRequest request);
}
