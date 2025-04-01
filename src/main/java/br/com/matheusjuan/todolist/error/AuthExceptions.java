package br.com.matheusjuan.todolist.error;

public class AuthExceptions {

    public static class JWTVerificationException extends RuntimeException {
        public JWTVerificationException() {
            super("Token inválido ou ausente");
        }
    }

    public static class JWTCreationException extends RuntimeException {
        public JWTCreationException() {
            super("Erro ao gerar token JWT");
        }
    }
}
