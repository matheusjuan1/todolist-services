package br.com.matheusjuan.todolist.model.dto.task;

import java.time.LocalDateTime;

public record TaskRequestDTO(
    String title,
    String description,
    LocalDateTime startAt,
    LocalDateTime endAt,
    Integer priority
) { }