package br.com.matheusjuan.todolist.model.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import br.com.matheusjuan.todolist.model.Role;

public record UserResponseDTO(
        UUID id,
        String username,
        String name,
        String token,
        LocalDateTime createdAt,
        List<Role> roles) {
}
