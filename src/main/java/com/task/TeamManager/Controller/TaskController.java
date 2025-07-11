package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Service.ProjectService;
import com.task.TeamManager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping("/{id}")
    public Tasks getById(@PathVariable long id){
        return taskService.getById(id);
    }
    @GetMapping
    public List<Tasks> getAll(){
        return  taskService.getallTasks();
    }
    @PostMapping("/{id}")
    public void SaveTasks(@RequestBody Tasks tasks){
        taskService.SaveTask(tasks);
    }
    @PutMapping("/{id}")
    public void Updatetask(@PathVariable long id, @RequestBody Tasks task){
        taskService.UpdateTask(task);
    }
    @DeleteMapping("/{id}")
    public  void DeleteProject(@PathVariable long id){
        taskService.DeleteTask(id);
    }



}
