package br.com.matheusjuan.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import br.com.matheusjuan.todolist.model.dto.auth.AuthRequestDTO;
import br.com.matheusjuan.todolist.model.dto.auth.RegisterRequestDTO;
import br.com.matheusjuan.todolist.model.dto.user.UserResponseDTO;
import br.com.matheusjuan.todolist.model.enums.RoleName;
import br.com.matheusjuan.todolist.error.UserExceptions.UserAlreadyExistsException;
import br.com.matheusjuan.todolist.error.UserExceptions.UserNotFoundException;
import br.com.matheusjuan.todolist.model.Role;
import br.com.matheusjuan.todolist.model.User;
import br.com.matheusjuan.todolist.repository.UserRepository;
import br.com.matheusjuan.todolist.security.JWTCreator;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTCreator jwtCreator;

    public UserResponseDTO registerUser(RegisterRequestDTO registerRequest) {

        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new UserAlreadyExistsException();
        }

        String passwordHashred = BCrypt.withDefaults().hashToString(12, registerRequest.password().toCharArray());

        User user = new User(
                registerRequest,
                passwordHashred,
                List.of(Role.builder().name(RoleName.ROLE_CUSTOMER).build()));

        User newUser = userRepository.save(user);

        String token = jwtCreator.generateToken(newUser.getId());

        return new UserResponseDTO(newUser, token);
    }

    public UserResponseDTO authenticate(AuthRequestDTO authRequest) {
        User user = userRepository.findByUsername(authRequest.username())
                .orElseThrow(() -> new UserNotFoundException("Credenciais inválidas"));

        Result passwordVerify = BCrypt.verifyer().verify(authRequest.password().toCharArray(), user.getPassword());

        if (!passwordVerify.verified) {
            throw new UserNotFoundException("Credenciais inválidas");
        }

        String token = jwtCreator.generateToken(user.getId());

        return new UserResponseDTO(user, token);
    }
}
