package br.com.matheusjuan.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheusjuan.todolist.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByUsername(String userName);
}
