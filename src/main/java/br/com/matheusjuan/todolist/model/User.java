package br.com.matheusjuan.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.matheusjuan.todolist.model.dto.auth.RegisterRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "user_id")
    private UUID id;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public User(RegisterRequestDTO dto, String passwordHashred) {
        this.username = dto.username();
        this.name = dto.name();
        this.password = passwordHashred;
    }

    public void setUsername(String username) throws Exception {
        if (username.length() > 20) {
            throw new Exception("O campo Username deve conter no máximo 20 caracteres");
        }
        this.username = username;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 255) {
            throw new Exception("O campo Nome deve conter no máximo 255 caracteres");
        }
        this.name = name;
    }
}
