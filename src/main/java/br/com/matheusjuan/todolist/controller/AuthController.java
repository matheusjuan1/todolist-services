package br.com.matheusjuan.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusjuan.todolist.dto.auth.AuthRequestDTO;
import br.com.matheusjuan.todolist.dto.auth.RegisterRequestDTO;
import br.com.matheusjuan.todolist.dto.user.UserResponseDTO;
import br.com.matheusjuan.todolist.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(description = "Cria usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna usuário criado"),
            @ApiResponse(responseCode = "409", description = "Usuário já existe")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        UserResponseDTO newUser = authService.registerUser(registerRequest);

        return ResponseEntity.ok().body(newUser);
    }

    @Operation(description = "Autentica usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna usuário autenticado"),
            @ApiResponse(responseCode = "404", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> auth(@RequestBody AuthRequestDTO userRequest) {
        UserResponseDTO user = authService.authenticate(userRequest);

        return ResponseEntity.ok().body(user);
    }
}