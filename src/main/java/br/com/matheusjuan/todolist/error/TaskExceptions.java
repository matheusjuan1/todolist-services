package br.com.matheusjuan.todolist.error;

public class TaskExceptions {

    public static class TaskNotFoundException extends RuntimeException {
        public TaskNotFoundException() {
            super("Tarefa não encontrada");
        }
    }

    public static class TaskDateException extends RuntimeException {
        public TaskDateException() {
            super("Data inválida");
        }

        public TaskDateException(String message) {
            super(message);
        }
    }

    public static class TaskPriorityException extends RuntimeException {
        public TaskPriorityException() {
            super("Prioridade inválida");
        }
    }
}
