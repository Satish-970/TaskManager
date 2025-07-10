package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Roles;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Repository.RoleRepository;
import com.task.TeamManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register user with roles
    public User registerUser(User user, Set<String> roleNames) {
        if (userRepository.existsByUsername(user.getname())) {
            throw new IllegalArgumentException("Username already exists: " + user.getname());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Roles> roles = resolveRoles(roleNames);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private Set<Roles> resolveRoles(Set<String> roleNames) {
        Set<Roles> roles = new HashSet<>();

        if (roleNames == null || roleNames.isEmpty()) {
            Roles defaultRole = roleRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER)
                    .orElseThrow(() -> new RuntimeException("Default role TEAM_MEMBER not found."));
            roles.add(defaultRole);
        } else {
            for (String roleName : roleNames) {
                Roles.ERole eRole;
                switch (roleName.toLowerCase()) {
                    case "manager":
                        eRole = Roles.ERole.ROLE_PROJECT_MANAGER;
                        break;
                    case "member":
                        eRole = Roles.ERole.ROLE_TEAM_MEMBER;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported role: " + roleName);
                }

                Roles role = roleRepository.findbyname(eRole)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        }
        return roles;
    }

    public Optional<User> findbyUserId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findbyUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUserRoles(Long userId, Set<String> newRoles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Set<Roles> resolvedRoles = resolveRoles(newRoles);
        user.setRoles(resolvedRoles);

        return userRepository.save(user);
    }
}