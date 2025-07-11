package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;
    }

    public void SaveProject(Projects projects){
        projectRepository.save(projects);
    }
    public List<Projects> getallProjects(){
        return projectRepository.findAll();
    }
    public Projects getById(long id){
        return projectRepository.getById(id);
    }
    public Projects GetProjectByManager(long id){
        return projectRepository.getById(id);
    }
    public void UpdateProjects(Projects project){
         projectRepository.save(project);
    }
    public void deleteProject(long id){
        projectRepository.deleteById(id);
    }
}
