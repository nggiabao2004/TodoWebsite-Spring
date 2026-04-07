package com.nggiabao2004.todowebsite_spring.controller;

import com.nggiabao2004.todowebsite_spring.dto.TodoRequestDto;
import com.nggiabao2004.todowebsite_spring.dto.TodoResponseDto;
import com.nggiabao2004.todowebsite_spring.security.CustomUserDetails;
import com.nggiabao2004.todowebsite_spring.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @Valid @RequestBody TodoRequestDto request) {
        TodoResponseDto createdTodo = todoService.createTodo(currentUser.getId(), request);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos(
            @AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(todoService.getAllTodosByUserId(currentUser.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoByIdAndUserId(id, currentUser.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDto request) {
        return ResponseEntity.ok(todoService.updateTodo(id, currentUser.getId(), request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @PathVariable Long id) {
        todoService.deleteTodo(id, currentUser.getId());
        return ResponseEntity.noContent().build();
    }
}
