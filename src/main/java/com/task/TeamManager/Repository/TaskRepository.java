package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Tasks;
import com.task.TeamManager.Model.Tasks.TaskPriority;
import com.task.TeamManager.Model.Tasks.TaskStatus;
import com.task.TeamManager.Model.Projects;
import com.task.TeamManager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    // Get tasks assigned to a specific user
    List<Tasks> findByAssignedTo(User user);

    // Get tasks under a specific project
    List<Tasks> findByProject(Projects project);

    // Get tasks by status
    List<Tasks> findByStatus(TaskStatus status);

    // Get tasks by priority
    List<Tasks> findByPriority(TaskPriority priority);

    // Custom query: Get all tasks with project and assigned user eagerly fetched
    @Query("SELECT t FROM Tasks t JOIN FETCH t.project JOIN FETCH t.assignedTo")
    List<Tasks> findAllWithProjectAndAssignee();
}
