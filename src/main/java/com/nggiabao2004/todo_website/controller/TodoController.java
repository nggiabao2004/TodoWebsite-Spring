package com.nggiabao2004.todo_website.controller;

import com.nggiabao2004.todo_website.dto.request.TodoCreateRequest;
import com.nggiabao2004.todo_website.dto.request.TodoUpdateRequest;
import com.nggiabao2004.todo_website.entity.Todo;
import com.nggiabao2004.todo_website.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    Todo createTodo(@RequestBody @Valid TodoCreateRequest request){
        return todoService.createTodo(request);
    }

    @GetMapping
    List<Todo> getListTodo(){
        return todoService.getListTodo();
    }

    @GetMapping("/{id}")
    Todo getTodoById(@PathVariable String id){
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    Todo updateTodoById(@PathVariable String id, @RequestBody @Valid TodoUpdateRequest request){
        return todoService.updateTodoById(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteTodoById(@PathVariable String id){
        todoService.deleteTodoById(id);
        return "Todo has been deleted!";
    }
}
