package br.com.matheusjuan.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusjuan.todolist.dto.UserRequestDTO;
import br.com.matheusjuan.todolist.dto.UserResponseDTO;
import br.com.matheusjuan.todolist.model.UserModel;
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
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserModel userModel) {
        UserResponseDTO newUser = authService.registerUser(userModel);

        return ResponseEntity.ok().body(newUser);
    }

    @Operation(description = "Autentica usuário")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> auth(@RequestBody UserRequestDTO userRequest) {
        UserResponseDTO user = authService.authenticate(userRequest);

        return ResponseEntity.ok().body(user);
    }
}