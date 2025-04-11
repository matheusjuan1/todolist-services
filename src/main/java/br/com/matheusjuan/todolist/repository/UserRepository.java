package br.com.matheusjuan.todolist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheusjuan.todolist.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}
