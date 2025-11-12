package com.nggiabao2004.todo_website.service;

import com.nggiabao2004.todo_website.dto.request.TodoCreateRequest;
import com.nggiabao2004.todo_website.dto.request.TodoUpdateRequest;
import com.nggiabao2004.todo_website.entity.Todo;
import com.nggiabao2004.todo_website.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(TodoCreateRequest request){


        Todo newTodo = new Todo();

        newTodo.setTitle(request.getTitle());
        newTodo.setDescription(request.getDescription());
        newTodo.setDeadline(request.getDeadline());
        newTodo.setCompleted(request.isCompleted());

        return todoRepository.save(newTodo);
    }

    public List<Todo> getListTodo(){
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found!"));
    }

    public Todo updateTodoById(String id, TodoUpdateRequest request){
        Todo updateTodo = getTodoById(id);

        updateTodo.setTitle(request.getTitle());
        updateTodo.setDescription(request.getDescription());
        updateTodo.setDeadline(request.getDeadline());
        updateTodo.setCompleted(request.isCompleted());

        return todoRepository.save(updateTodo);
    }

    public void deleteTodoById(String id){
        todoRepository.deleteById(id);
    }
}
