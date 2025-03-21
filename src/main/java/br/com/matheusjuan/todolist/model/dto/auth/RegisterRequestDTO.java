package br.com.matheusjuan.todolist.model.dto.auth;

public record RegisterRequestDTO(
        String name,
        String username,
        String password) {
}
