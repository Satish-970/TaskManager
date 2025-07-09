package com.task.TeamManager.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public enum ERole{
        ROLE_TEAM_MEMBER,
        ROLE_PROJECT_MANAGER
    }

    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private ERole name;

}
