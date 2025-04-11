package br.com.matheusjuan.todolist.model.dto.task;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.matheusjuan.todolist.model.Task;

public record TaskResponseDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime startAt,
    LocalDateTime endAt,
    LocalDateTime createdAt,
    Integer priority,
    Integer version
) {
    public TaskResponseDTO(Task task) {
        this(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStartAt(),
            task.getEndAt(),
            task.getCreatedAt(),
            task.getPriority(),
            task.getVersion()
        );
    }
}
