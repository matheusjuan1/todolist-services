package br.com.matheusjuan.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheusjuan.todolist.dto.UserRequestDTO;
import br.com.matheusjuan.todolist.dto.UserResponseDTO;
import br.com.matheusjuan.todolist.error.AuthExceptions;
import br.com.matheusjuan.todolist.model.UserModel;
import br.com.matheusjuan.todolist.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public UserResponseDTO registerUser(UserModel user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new AuthExceptions.UserAlreadyExistsException("O username já está em uso!");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passwordHashred);

        UserModel newUser = userRepository.save(user);

        String token = jwtService.generateToken(newUser.getId());

        return new UserResponseDTO(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getName(),
                token,
                newUser.getCreatedAt());
    }

    public UserResponseDTO authenticate(UserRequestDTO userRequest) {
        UserModel user = userRepository.findByUsername(userRequest.username());

        if (user == null) {
            throw new AuthExceptions.UserNotFoundException("Credenciais inválidas");
        }

        var passwordVerify = BCrypt.verifyer().verify(userRequest.password().toCharArray(), user.getPassword());

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
