package br.com.matheusjuan.todolist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.matheusjuan.todolist.model.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}
