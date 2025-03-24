package br.com.matheusjuan.todolist.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.matheusjuan.todolist.model.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    User findByUsername(String userName);
}
