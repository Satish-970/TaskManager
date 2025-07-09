package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {

    // Get all projects with project manager details (eager fetch)
    @Query("SELECT p FROM Projects p JOIN FETCH p.projectManager")
    List<Projects> findAllWithProjectManager();

    // Get all projects managed by a specific user
    List<Projects> findByProjectManager(User projectManager);

    // Optional: Get project by name (if project names are unique)
    Projects findByName(String name);
}
