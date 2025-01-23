package br.com.matheusjuan.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheusjuan.todolist.model.TaskModel;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
}
