package br.com.matheusjuan.todolist.model.dto.error;

public record ErrorResponseDTO(
        int errorCode,
        String errorMessage,
        String errorType) {
}