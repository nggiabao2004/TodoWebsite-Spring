package com.nggiabao2004.todowebsite_spring.service;

import com.nggiabao2004.todowebsite_spring.dto.TodoRequestDto;
import com.nggiabao2004.todowebsite_spring.dto.TodoResponseDto;
import com.nggiabao2004.todowebsite_spring.exception.ResourceNotFoundException;
import com.nggiabao2004.todowebsite_spring.model.Todo;
import com.nggiabao2004.todowebsite_spring.model.User;
import com.nggiabao2004.todowebsite_spring.repository.TodoRepository;
import com.nggiabao2004.todowebsite_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public TodoResponseDto createTodo(Long userId, TodoRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.isCompleted())
                .user(user)
                .build();
        
        Todo savedTodo = todoRepository.save(todo);
        return mapToDto(savedTodo);
    }

    @Override
    public List<TodoResponseDto> getAllTodosByUserId(Long userId) {
        List<Todo> todos = todoRepository.findByUser_Id(userId);
        return todos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TodoResponseDto getTodoByIdAndUserId(Long id, Long userId) {
        Todo todo = checkAndGetTodoOwnership(id, userId);
        return mapToDto(todo);
    }

    @Override
    public TodoResponseDto updateTodo(Long id, Long userId, TodoRequestDto request) {
        Todo todo = checkAndGetTodoOwnership(id, userId);

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return mapToDto(updatedTodo);
    }

    @Override
    public void deleteTodo(Long id, Long userId) {
        Todo todo = checkAndGetTodoOwnership(id, userId);
        todoRepository.delete(todo);
    }

    private Todo checkAndGetTodoOwnership(Long todoId, Long userId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + todoId));

        if (!todo.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Todo not found or does not belong to you");
        }
        return todo;
    }

    private TodoResponseDto mapToDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}
