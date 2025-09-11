package com.nggiabao2004.todo_website.repository;

import com.nggiabao2004.todo_website.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
}
