package br.com.matheusjuan.todolist.dto.auth;

public record RegisterRequestDTO(
        String name,
        String username,
        String password) {
}
