package com.nggiabao2004.todowebsite_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
