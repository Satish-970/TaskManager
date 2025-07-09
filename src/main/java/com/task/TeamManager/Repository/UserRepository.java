package com.task.TeamManager.Repository;

import com.task.TeamManager.Model.Roles.ERole;
import com.task.TeamManager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by username (used in login)
    Optional<User> findByUsername(String username);

    // Find by email (for uniqueness or verification)
    Optional<User> findByEmail(String email);

    // Find all users with a specific role
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role")
    List<User> findByRole(ERole role);

    // Check if a user exists by username or email
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
