package br.com.matheusjuan.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.matheusjuan.todolist.model.dto.task.TaskRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Version
    private int version;


    public Task(TaskRequestDTO dto, User user) {
        this.title = dto.title();
        this.description = dto.description();
        this.startAt = dto.endAt();
        this.endAt = dto.endAt();
        this.priority = dto.priority();
        this.user = user;
    }
}
