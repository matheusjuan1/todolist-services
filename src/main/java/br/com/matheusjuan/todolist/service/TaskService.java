package br.com.matheusjuan.todolist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusjuan.todolist.error.TaskExceptions;
import br.com.matheusjuan.todolist.error.UserExceptions;
import br.com.matheusjuan.todolist.error.UserExceptions.UserNotFoundException;
import br.com.matheusjuan.todolist.model.Task;
import br.com.matheusjuan.todolist.model.User;
import br.com.matheusjuan.todolist.model.dto.task.TaskRequestDTO;
import br.com.matheusjuan.todolist.model.dto.task.TaskResponseDTO;
import br.com.matheusjuan.todolist.repository.TaskRepository;
import br.com.matheusjuan.todolist.repository.UserRepository;
import br.com.matheusjuan.todolist.util.Util;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskResponseDTO create(TaskRequestDTO taskRequest, UUID idUser) {
        User user = this.userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException());

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

        return new TaskResponseDTO(taskRepository.save(new Task(taskRequest, user)));
    }

    public List<TaskResponseDTO> listByUser(UUID idUser) {
        List<Task> list = this.taskRepository.findAllByUserId(idUser);

        return list.stream().map(TaskResponseDTO::new).toList();
    }

    public TaskResponseDTO update(TaskRequestDTO taskRequest, UUID idTask, UUID idUser) {
        Task task = this.taskRepository.findById(idTask)
                .orElseThrow(() -> new TaskExceptions.TaskNotFoundException());

        User user = this.userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException());

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UserExceptions.UserUnauthorizedException("Usuário não tem permissão para alterar essa tarefa");
        }

        Util.copyNonNullProperties(taskRequest, task);

        return new TaskResponseDTO(taskRepository.save(task));
    }

}
