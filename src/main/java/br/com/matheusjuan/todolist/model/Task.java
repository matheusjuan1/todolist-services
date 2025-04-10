package br.com.matheusjuan.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.matheusjuan.todolist.model.dto.task.TaskRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_task")
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 255)
    private String description;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Column(nullable = false)
    private Integer priority;
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Version
    private int version;


    public Task(TaskRequestDTO dto, UUID idUser) {
        this.title = dto.title();
        this.description = dto.description();
        this.startAt = dto.endAt();
        this.endAt = dto.endAt();
        this.priority = dto.priority();
        this.idUser = idUser;
    }

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O campo Título deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }

    public void setDescription(String description) throws Exception {
        if (description.length() > 255) {
            throw new Exception("O campo Título deve conter no máximo 255 caracteres");
        }
        this.description = description;
    }
}
