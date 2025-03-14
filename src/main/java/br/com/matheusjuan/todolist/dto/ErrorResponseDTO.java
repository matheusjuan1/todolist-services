package br.com.matheusjuan.todolist.dto;

public record ErrorResponseDTO(
        int errorCode,
        String errorMessage,
        String errorType) {
}