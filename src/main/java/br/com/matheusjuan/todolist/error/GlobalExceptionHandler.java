package br.com.matheusjuan.todolist.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.matheusjuan.todolist.model.dto.error.ErrorResponseDTO;
import br.com.matheusjuan.todolist.error.AuthExceptions.JwtAuthenticationException;
import br.com.matheusjuan.todolist.error.AuthExceptions.UserAlreadyExistsException;
import br.com.matheusjuan.todolist.error.AuthExceptions.UserNotFoundException;
import br.com.matheusjuan.todolist.error.TaskExceptions.TaskDateException;
import br.com.matheusjuan.todolist.error.TaskExceptions.TaskPriorityException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(001, e.getMostSpecificCause().getMessage(),
                "HTTP_MESSAGE_NOT_READABLE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        ErrorResponseDTO error = new ErrorResponseDTO(500, e.getMessage(), "INTERNAL_SERVER_ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // AUTH - USER EXCEPTIONS

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleJwtAuthenticationException(JwtAuthenticationException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(100, e.getMessage(), "INVALID_JWT");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(200, e.getMessage(), "USER_ALREADY_EXISTS");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(201, e.getMessage(), "USER_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // TASK EXCEPTIONS

    @ExceptionHandler(TaskDateException.class)
    public ResponseEntity<ErrorResponseDTO> handleTaskDateException(TaskDateException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(300, e.getMessage(), "INVALID_DATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TaskPriorityException.class)
    public ResponseEntity<ErrorResponseDTO> handleTaskPriorityException(TaskPriorityException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(301, e.getMessage(), "INVALID_PRIORITY");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
