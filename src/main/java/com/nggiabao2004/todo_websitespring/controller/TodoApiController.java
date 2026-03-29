package com.nggiabao2004.todo_websitespring.controller;

import com.nggiabao2004.todo_websitespring.dto.TodoCreateRequest;
import com.nggiabao2004.todo_websitespring.dto.TodoUpdateRequest;
import com.nggiabao2004.todo_websitespring.model.Todo;
import com.nggiabao2004.todo_websitespring.model.User;
import com.nggiabao2004.todo_websitespring.repository.UserRepository;
import com.nggiabao2004.todo_websitespring.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoApiController {

    @Autowired
    private TodoService todoService;
    
    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    @GetMapping
    public List<Todo> getAllTodos(Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        return todoService.getAllTodos(user.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        Todo todo = todoService.getTodoById(id, user.getId());
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoCreateRequest request, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        
        Todo newTodo = new Todo();
        newTodo.setTitle(request.getTitle());
        newTodo.setDescription(request.getDescription());
        newTodo.setCompleted(false);
        newTodo.setUser(user); // Set ownership
        
        Todo createdTodo = todoService.createTodo(newTodo);
        return ResponseEntity.ok(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest request, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        
        Todo updatedTodo = new Todo();
        updatedTodo.setTitle(request.getTitle());
        updatedTodo.setDescription(request.getDescription());
        updatedTodo.setCompleted(request.isCompleted());
        
        Todo result = todoService.updateTodo(id, updatedTodo, user.getId());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        todoService.deleteTodo(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
