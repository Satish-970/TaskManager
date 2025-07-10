package com.task.TeamManager.Dto;

import com.task.TeamManager.Model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {
    private long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private User projectManager;
    private String createdAt;

}
