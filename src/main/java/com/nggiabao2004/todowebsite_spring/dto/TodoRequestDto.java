package com.nggiabao2004.todowebsite_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private boolean completed;
}
