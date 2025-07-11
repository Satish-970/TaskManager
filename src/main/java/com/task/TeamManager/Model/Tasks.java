package com.task.TeamManager.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.util.Date;
@Entity
@Data
@Setter
@Getter
@Component
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    public enum TaskStatus {
        TO_DO, IN_PROGRESS, COMPLETED, BLOCKED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TO_DO;

    public enum TaskPriority {
        LOW, MEDIUM, HIGH, URGENT
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id", nullable = false)
    private User assignedTo;

    @CreationTimestamp
    private Date createdAt;
    public Long getId(){
        return id;
    }
}
