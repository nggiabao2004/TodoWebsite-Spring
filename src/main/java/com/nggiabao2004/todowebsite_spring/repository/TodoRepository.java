package com.nggiabao2004.todowebsite_spring.repository;

import com.nggiabao2004.todowebsite_spring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser_Id(Long userId);
}
