package com.task.TeamManager.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TeamUsers")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank(message = "Name is required")
    @Column(unique = true, nullable = false)
    private String name;

    @Email
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private Set<Roles> roles;

    public String getname() {
        return  name;
    }


}
