package br.com.matheusjuan.todolist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusjuan.todolist.model.Task;
import br.com.matheusjuan.todolist.model.dto.task.TaskRequestDTO;
import br.com.matheusjuan.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(description = "Cria tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna tarefa criada"),
            @ApiResponse(responseCode = "400", description = "A data de início/data de término deve ser maior do que a data atual ou A data de início deve ser menor do que a data de término")
    })
    @PostMapping("/")
    public ResponseEntity<Task> create(@RequestBody TaskRequestDTO taskRequest, HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        Task task = this.taskService.create(taskRequest, idUser);

        return ResponseEntity.ok().body(task);
    }

    @Operation(description = "Lista tarefas do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna tarefas do usuário")
    })
    @GetMapping("/")
    public ResponseEntity<List<Task>> listByUser(HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        List<Task> list = this.taskService.listByUser(idUser);

        return ResponseEntity.ok().body(list);
    }

    @Operation(description = "Edita a tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna tarefa editada"),
            @ApiResponse(responseCode = "401", description = "Usuário não tem permissão para alterar essa tarefa"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task taskRequest, HttpServletRequest request,
            @PathVariable UUID id) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        Task taskUpdated = this.taskService.update(taskRequest, id, idUser);

        return ResponseEntity.ok().body(taskUpdated);
    }
}