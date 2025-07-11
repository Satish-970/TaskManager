package com.task.TeamManager.Controller;

import com.task.TeamManager.Model.User;
import com.task.TeamManager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
     List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> getUserById(@PathVariable  Long id) {
        Optional<User> user = userService.findbyUserId(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/roles")
    public ResponseEntity<User> updateUserRoles(@PathVariable Long id, @RequestBody Set<String> roles) {
         User updatedUser = userService.updateUserRoles(id, roles);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws Exception {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();

    }
}
