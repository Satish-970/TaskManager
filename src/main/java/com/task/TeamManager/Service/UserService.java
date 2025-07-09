package com.task.TeamManager.Service;


import com.task.TeamManager.Model.Roles;
import com.task.TeamManager.Model.User;
import com.task.TeamManager.Repository.RoleRepository;
import com.task.TeamManager.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User registerUser(User user) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getname())) {
            throw new RuntimeException("Username already taken");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getname());
        user.setEmail(user.getEmail());
        // Assign default role
        Optional<Roles> defaultRole = roleRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER);
        if (defaultRole.isEmpty()) {
            throw new RuntimeException("Default role not found");
        }

        user.setRoles(Collections.singleton(defaultRole.get()));

        // Save and return
        return userRepository.save(user);
    }
  public Optional<User> findbyUserId(Long id){
        return userRepository.findById(id);
  }
  public Optional<User> findbyUsername(String name){
        return  userRepository.findByUsername(name);
  }

  public List<User> FindAllUsers(){
        return userRepository.findAll();
  }

  public  void DeleteUser(long id) throws Exception{
        if (!userRepository.existsById(id)) {
            throw new Exception("User not found");
        }
        userRepository.deleteById(id);
  }
public User UpdateUser(User user) throws Exception {
       if(roleRepository .findbyname(Roles.ERole.ROLE_TEAM_MEMBER).isEmpty()){
           throw new Exception("Role not found");
       }
       if(roleRepository.findbyname(Roles.ERole.ROLE_PROJECT_MANAGER).isPresent()){
           user.setRoles(Collections.singleton(roleRepository.findbyname(Roles.ERole.ROLE_TEAM_MEMBER).get()));
       }
        return userRepository.save(user);
    }

}
