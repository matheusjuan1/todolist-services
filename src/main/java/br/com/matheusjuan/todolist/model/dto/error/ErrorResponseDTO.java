package br.com.matheusjuan.todolist.model.dto.error;

import java.util.Date;

public record ErrorResponseDTO(
                int errorCode,
                String errorMessage,
                String errorType,
                Date timestamp) {

        public ErrorResponseDTO(int errorCode, String errorMessage, String errorType) {
                this(errorCode, errorMessage, errorType, new Date());
        }
}