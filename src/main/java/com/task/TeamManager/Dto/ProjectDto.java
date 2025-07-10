package com.task.TeamManager.Dto;

import com.task.TeamManager.Model.User;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
    private long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private User projectManager;
    private String createdAt;

}
