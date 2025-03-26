package br.com.matheusjuan.todolist.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.matheusjuan.todolist.model.dto.error.ErrorResponseDTO;
import br.com.matheusjuan.todolist.error.AuthExceptions.JWTCreationException;
import br.com.matheusjuan.todolist.error.AuthExceptions.JWTVerificationException;
import br.com.matheusjuan.todolist.error.UserExceptions.UserAlreadyExistsException;
import br.com.matheusjuan.todolist.error.UserExceptions.UserNotFoundException;
import br.com.matheusjuan.todolist.error.TaskExceptions.TaskDateException;
import br.com.matheusjuan.todolist.error.TaskExceptions.TaskNotFoundException;
import br.com.matheusjuan.todolist.error.TaskExceptions.TaskPriorityException;
import br.com.matheusjuan.todolist.error.UserExceptions.UserUnauthorizedException;

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

    // AUTH EXCEPTIONS

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ErrorResponseDTO> handleJWTCreationException(JWTCreationException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(1000, e.getMessage(), "JWT_ERROR_CREATE");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponseDTO> handleJWTVerificationException(JWTVerificationException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(1001, e.getMessage(), "INVALID_JWT");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // USER EXCEPTIONS

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(2000, e.getMessage(), "USER_ALREADY_EXISTS");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(2001, e.getMessage(), "USER_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserUnauthorizedException(UserUnauthorizedException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(2002, e.getMessage(), "USER_UNAUTHORIZED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // TASK EXCEPTIONS

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTaskNotFoundException(TaskNotFoundException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(3000, e.getMessage(), "TASK_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TaskDateException.class)
    public ResponseEntity<ErrorResponseDTO> handleTaskDateException(TaskDateException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(3001, e.getMessage(), "TASK_INVALID_DATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TaskPriorityException.class)
    public ResponseEntity<ErrorResponseDTO> handleTaskPriorityException(TaskPriorityException e) {
        ErrorResponseDTO error = new ErrorResponseDTO(3002, e.getMessage(), "TASK_INVALID_PRIORITY");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
