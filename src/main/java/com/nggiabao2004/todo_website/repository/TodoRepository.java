package com.nggiabao2004.todo_website.repository;

import com.nggiabao2004.todo_website.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    boolean existsByTitle(String title);
    List<Todo> findByUserId(Long userId);
}
