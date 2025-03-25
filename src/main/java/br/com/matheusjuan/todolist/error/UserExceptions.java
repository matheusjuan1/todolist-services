package br.com.matheusjuan.todolist.error;

public class UserExceptions {

    public static class UserUnauthorizedException extends RuntimeException {
        public UserUnauthorizedException() {
            super("Usuário não autorizado");
        }

        public UserUnauthorizedException(String message) {
            super(message);
        }
    }

}
