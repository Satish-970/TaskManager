package com.task.TeamManager.Service;

import com.task.TeamManager.Model.Roles;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Repository.RoleRepository;
import com.task.TeamManager.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register user with dynamic roles
    public User registerUser(User user, Set<String> strRoles) {
        if (userRepository.existsByUsername(user.getname())) {
            throw new IllegalArgumentException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getname());
        user.setEmail(user.getEmail());

        Set<Roles> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Roles teamMemberRole = roleRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER)
                    .orElseThrow(() -> new RuntimeException("Error: Role 'TEAM_MEMBER' not found."));
            roles.add(teamMemberRole);
        } else {
            for (String role : strRoles) {
                switch (role.toLowerCase()) {
                    case "manager":
                        Roles managerRole = roleRepository.findbyname(Roles.ERole.ROLE_PROJECT_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role 'PROJECT_MANAGER' not found."));
                        roles.add(managerRole);
                        break;
                    case "member":
                        Roles memberRole = roleRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER)
                                .orElseThrow(() -> new RuntimeException("Error: Role 'TEAM_MEMBER' not found."));
                        roles.add(memberRole);
                        break;
                    default:
                        throw new RuntimeException("Error: Role '" + role + "' is not supported.");
                }
            }
        }

        user.setRoles(roles);
        return userRepository.save(user);
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

    public void deleteUser(long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Update user roles using userId and newRoles
    public User updateUserRoles(Long userId, Set<String> newRoles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Set<Roles> roles = new HashSet<>();

        for (String roleName : newRoles) {
            Roles.ERole eRole;
            switch (roleName.toLowerCase()) {
                case "manager":
                    eRole = Roles.ERole.ROLE_PROJECT_MANAGER;
                    break;
                case "member":
                    eRole = Roles.ERole.ROLE_TEAM_MEMBER;
                    break;
                default:
                    throw new IllegalArgumentException("Role '" + roleName + "' is not supported.");
            }

            Roles role = roleRepository.findbyname(eRole)
                    .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' not found."));
            roles.add(role);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }
}
