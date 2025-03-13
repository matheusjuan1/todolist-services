package br.com.matheusjuan.todolist.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.matheusjuan.todolist.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, UUID> {

    UserModel findByUsername(String userName);
}
