package br.com.matheusjuan.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheusjuan.todolist.dto.auth.AuthRequestDTO;
import br.com.matheusjuan.todolist.dto.auth.RegisterRequestDTO;
import br.com.matheusjuan.todolist.dto.user.UserResponseDTO;
import br.com.matheusjuan.todolist.error.AuthExceptions;
import br.com.matheusjuan.todolist.model.User;
import br.com.matheusjuan.todolist.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public UserResponseDTO registerUser(RegisterRequestDTO registerRequest) {

        if (userRepository.findByUsername(registerRequest.username()) != null) {
            throw new AuthExceptions.UserAlreadyExistsException();
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, registerRequest.password().toCharArray());

        User user = new User(
                registerRequest.username(),
                registerRequest.name(),
                passwordHashred.toString());

        User newUser = userRepository.save(user);

        String token = jwtService.generateToken(newUser.getId());

        return new UserResponseDTO(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getName(),
                token,
                newUser.getCreatedAt());
    }

    public UserResponseDTO authenticate(AuthRequestDTO authRequest) {
        User user = userRepository.findByUsername(authRequest.username());

        if (user == null) {
            throw new AuthExceptions.UserNotFoundException("Credenciais inválidas");
        }

        var passwordVerify = BCrypt.verifyer().verify(authRequest.password().toCharArray(), user.getPassword());

        if (!passwordVerify.verified) {
            throw new AuthExceptions.UserNotFoundException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(user.getId());

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                token,
                user.getCreatedAt());
    }
}
