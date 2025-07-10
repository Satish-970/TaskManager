package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public String isTaskValid(String taskname) {
        taskname = taskname.trim();
        if (taskname.isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
        return  taskname;

    }

    public  String DuedateValidate(Date duedate) {
        if (duedate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        Date currentDate = new Date();
        if (duedate.before(currentDate)) {
            throw new IllegalArgumentException("Due date cannot be in the past");
        }
        return "Due date is valid";
    }

    public Tasks GetTasks(Long taskId) {
        if (taskId == null || taskId <= 0) {
            throw new IllegalArgumentException("Invalid task ID");
        }
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
    }

    public Tasks SaveTask(Tasks task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (task.getDueDate() == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        return (Tasks) taskRepository.save(task);
    }
    public void DeleteTask(Long taskId) {
        if (taskId == null || taskId <= 0) {
            throw new IllegalArgumentException("Invalid task ID");
        }
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Task not found with ID: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }


    public Page<Tasks> getAllTasks(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }
        return taskRepository.findAll(pageable);
    }

    public Page<Tasks> getTasksByAssignedUser(User user, Pageable pageable) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }
        return (Page<Tasks>) taskRepository.findByAssignedTo(user, pageable);
    }

    public Page<Tasks> getTasksByProject(Long projectId, Pageable pageable) {
        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }
        Projects project = new Projects();
        project.setId(projectId);
        return (Page<Tasks>) taskRepository.findByProject(project, pageable);
    }

    public Page<Tasks> getTasksByStatus(String status, Pageable pageable) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }
        Tasks.TaskStatus taskStatus;
        try {
            taskStatus = Tasks.TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task status: " + status);
        }
        return (Page<Tasks>) taskRepository.findByStatus(taskStatus);
    }

    public Page<Tasks> getTasksByPriority(String priority, Pageable pageable) {
        if (priority == null || priority.isEmpty()) {
            throw new IllegalArgumentException("Priority cannot be null or empty");
        }
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }
        Tasks.TaskPriority taskPriority;
        try {
            taskPriority = Tasks.TaskPriority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid task priority: " + priority);
        }
        return (Page<Tasks>) taskRepository.findByPriority(taskPriority);
    }
}
