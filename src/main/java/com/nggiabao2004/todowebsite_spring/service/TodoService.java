package com.nggiabao2004.todowebsite_spring.service;

import com.nggiabao2004.todowebsite_spring.dto.TodoRequestDto;
import com.nggiabao2004.todowebsite_spring.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(Long userId, TodoRequestDto todoRequestDto);
    List<TodoResponseDto> getAllTodosByUserId(Long userId);
    TodoResponseDto getTodoByIdAndUserId(Long id, Long userId);
    TodoResponseDto updateTodo(Long id, Long userId, TodoRequestDto todoRequestDto);
    void deleteTodo(Long id, Long userId);
}
