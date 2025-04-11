package br.com.matheusjuan.todolist.model.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.matheusjuan.todolist.model.User;

public record UserResponseDTO(
                UUID id,
                String username,
                String name,
                String token,
                LocalDateTime createdAt) {

        public UserResponseDTO(User user, String token) {
                this(
                                user.getId(),
                                user.getUsername(),
                                user.getName(),
                                token,
                                user.getCreatedAt());
        }
}
