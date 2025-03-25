package br.com.matheusjuan.todolist.error;

public class AuthExceptions {

    public static class JwtAuthenticationException extends RuntimeException {
        public JwtAuthenticationException() {
            super("Token inválido ou ausente");
        }
    }
}
