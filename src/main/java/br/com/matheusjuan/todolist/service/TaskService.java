package br.com.matheusjuan.todolist.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusjuan.todolist.error.TaskExceptions;
import br.com.matheusjuan.todolist.model.Task;
import br.com.matheusjuan.todolist.model.dto.task.TaskRequestDTO;
import br.com.matheusjuan.todolist.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(TaskRequestDTO taskRequest, UUID idUser) {
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(taskRequest.startAt()) || currentDate.isAfter(taskRequest.endAt())) {
            throw new TaskExceptions.TaskDateException(
                    "A data de início/data de término deve ser maior do que a data atual");
        }

        if (taskRequest.startAt().isAfter(taskRequest.endAt())) {
            throw new TaskExceptions.TaskDateException("A data de início deve ser menor do que a data de término");
        }

        if (!List.of(1, 2, 3).contains(taskRequest.priority())) {
            throw new TaskExceptions.TaskPriorityException();
        }

        return this.taskRepository.save(new Task(taskRequest, idUser));
    }

    public List<Task> listByUser(UUID idUser) {
        List<Task> list = this.taskRepository.findByIdUser(idUser).orElse(new ArrayList<>());

        return list;
    }

}
