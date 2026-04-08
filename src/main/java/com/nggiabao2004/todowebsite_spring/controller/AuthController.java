package com.nggiabao2004.todowebsite_spring.controller;

import com.nggiabao2004.todowebsite_spring.dto.AuthRequestDto;
import com.nggiabao2004.todowebsite_spring.dto.AuthResponseDto;
import com.nggiabao2004.todowebsite_spring.dto.RegisterRequestDto;
import com.nggiabao2004.todowebsite_spring.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody AuthRequestDto loginRequest,
            HttpServletRequest request) {
        return authService.login(loginRequest, request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}

