package com.Eventify.Eventify.service;

import com.Eventify.Eventify.Entity.*;
import com.Eventify.Eventify.repository.*;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.*;


@Service
public class RoleService {
    private final RoleRepository repo;

    public RoleService(RoleRepository repo){
        this.repo = repo;
    }

    public Role create(String name){
        Role r = new Role();
        r.setName(name);
        return repo.save(r);
    }

    public Role get(String name){
        return repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
}
