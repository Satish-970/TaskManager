package com.task.TeamManager.Repository;
import com.task.TeamManager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by username (used in login)
    Optional<User> findByUsername(String username);
    Optional<User> findbyEmail(String email);

    // Find by email (for uniqueness or verification)
       boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
