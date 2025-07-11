package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService=projectService;
    }

    @GetMapping("/{id}")
    public Projects getById(@PathVariable long id){
        return projectService.getById(id);
    }
    @GetMapping
    public List<Projects> getAll(){
       return  projectService.getallProjects();
    }
    @PostMapping("/{id}")
    public void SaveProjects(@RequestBody Projects projects){
        projectService.SaveProject(projects);
    }
    @PutMapping("/{id}")
    public void UpdateProject(@PathVariable long id, @RequestBody Projects projects){
        projectService.UpdateProjects(projects);
    }
    @DeleteMapping("/{id}")
    public  void DeleteProject(@PathVariable long id){
        projectService.deleteProject(id);
    }
}
