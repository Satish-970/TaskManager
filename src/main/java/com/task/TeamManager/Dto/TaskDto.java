package com.task.TeamManager.Dto;

import java.time.LocalDate;


public class TaskDto {
    private long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private long projectId;
    private long assignedToId;
    private LocalDate createdAt;

}
