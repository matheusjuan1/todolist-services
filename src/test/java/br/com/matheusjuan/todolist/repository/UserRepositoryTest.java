package br.com.matheusjuan.todolist.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.matheusjuan.todolist.model.User;
import br.com.matheusjuan.todolist.model.dto.auth.RegisterRequestDTO;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findByUsernameSuccess() {
        String username = "matheusteste";
        RegisterRequestDTO data = new RegisterRequestDTO("Matheus", username, "12345");
        this.createUser(data);
        
        User result = this.userRepository.findByUsername(username);

        assertThat(result != null).isTrue();
    }

    @Test
    void findByUsernameError() {
        String username = "matheusteste";
        
        User result = this.userRepository.findByUsername(username);

        assertThat(result == null).isTrue();
    }


    private User createUser(RegisterRequestDTO data) {
        User newUser = User.fromDTO(data, "12345");
        this.entityManager.persist(newUser);
        return newUser;
    }
}
