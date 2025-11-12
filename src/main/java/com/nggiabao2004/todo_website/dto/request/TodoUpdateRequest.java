package com.nggiabao2004.todo_website.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest {
    @Size(min = 3, message="Title-Todo khong duoc de trong!")
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean completed;
}
