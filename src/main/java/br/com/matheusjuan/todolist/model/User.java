package br.com.matheusjuan.todolist.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.matheusjuan.todolist.model.dto.auth.RegisterRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(RegisterRequestDTO dto, String passwordHashred, List<Role> roles) {
        this.username = dto.username();
        this.name = dto.name();
        this.password = passwordHashred;
        this.roles = roles;
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
