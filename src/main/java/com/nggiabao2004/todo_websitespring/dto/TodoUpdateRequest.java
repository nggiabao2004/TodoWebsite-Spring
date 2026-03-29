package com.nggiabao2004.todo_websitespring.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest {
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;
    private String description;
    private boolean completed;
}
