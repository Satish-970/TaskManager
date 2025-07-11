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

        return userRepository.save(user);
    }
}