package com.Eventify.Eventify.service;


import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.exception.UserNotFoundException;
import com.Eventify.Eventify.exception.UsernameAlreadyExistsException;
import com.Eventify.Eventify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final RoleService roleService;

    public UserService(UserRepository repo, RoleService roleService){
        this.repo = repo;
        this.roleService = roleService;
    }

    public User createUser(String name, String email, String password){
        if (repo.findByEmail(email).isPresent())
            throw new UsernameAlreadyExistsException("Email '" + email + "' already exist");
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);

        u.setRole(roleService.get("ROLE_USER"));

        return repo.save(u);
    }

    public List<User> all(){
        return repo.findAll();
    }

    public User updateRole(Long id, String roleName){
        User u = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        u.setRole(roleService.get(roleName));
        return repo.save(u);
    }
}
