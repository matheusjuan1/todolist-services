package br.com.matheusjuan.todolist.dto.error;

public record ErrorResponseDTO(
        int errorCode,
        String errorMessage,
        String errorType) {
}