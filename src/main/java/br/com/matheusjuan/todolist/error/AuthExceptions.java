package br.com.matheusjuan.todolist.error;

public class AuthExceptions {

    public static class UserAlreadyExistsException extends RuntimeException {

        public UserAlreadyExistsException() {
            super("Usuário já existe");
        }

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {

        public UserNotFoundException() {
            super("Usuário não encontrado");
        }

        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
