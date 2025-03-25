package br.com.matheusjuan.todolist.error;

public class UserExceptions {

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException() {
            super("Usuário já existe");
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

    public static class UserUnauthorizedException extends RuntimeException {
        public UserUnauthorizedException() {
            super("Usuário não autorizado");
        }

        public UserUnauthorizedException(String message) {
            super(message);
        }
    }

}
