package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("{id}")
    public Tasks getTaskById(@PathVariable Long id) {
        return taskService.GetTasks(id);
    }
    @GetMapping("/page")
    public Page<Tasks> getUsers(Pageable pageable){
        return taskService.getAllTasks(pageable);
    }
    @GetMapping("/assigned/{userId}")
    public Page<Tasks> getTasksByAssignedUser(@PathVariable Long userId, Pageable pageable) {
        User user = new User();
        user.setId(userId);
        return taskService.getTasksByAssignedUser(user, pageable);
    }
    @GetMapping("/project/{projectId}")
    public Page<Tasks> getTasksByProject(@PathVariable Long projectId, Pageable pageable) {
        return taskService.getTasksByProject(projectId, pageable);
    }
    @GetMapping("/status/{status}")
    public Page<Tasks> getTasksByStatus(@PathVariable String status, Pageable pageable) {
        return taskService.getTasksByStatus(status, pageable);
    }
    @GetMapping("/priority/{priority}")
    public Page<Tasks> getTasksByPriority(@PathVariable String priority, Pageable pageable)
    {
        return taskService.getTasksByPriority(priority, pageable);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.DeleteTask(id);
    }


}
