package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Roles;
import com.task.TeamManager.Service.ProjectService;
import com.task.TeamManager.Service.TaskService;
import com.task.TeamManager.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins="*",maxAge=3600)

public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

@PostMapping("{id}/api/tasks")
    public  ResponseEntity<Roles> Authorization(Roles role){
    if(!role.equals("PROJECT_MANAGER")){
        throw new IllegalArgumentException("Only Project Manager allowed");
    }
    return ResponseEntity.ok(role);
}

@Valid
public ResponseEntity<Projects> ValidateProjects(Projects projects){
    return ResponseEntity.ok(projects);
}


}
