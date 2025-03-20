package br.com.matheusjuan.todolist.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String name,
        String token,
        LocalDateTime createdAt) {
}
