package com.Eventify.Eventify.controller;

import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service){ this.service = service; }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,String> body){
        User u = service.createUser(body.get("name"), body.get("email"), body.get("password"));
        return ResponseEntity.ok(u);
    }


    @PutMapping("/{id}/role")
    public ResponseEntity<?> role(@PathVariable Long id, @RequestBody Map<String,String> body){
        return ResponseEntity.ok(service.updateRole(id, body.get("role")));
    }


    @GetMapping
    public ResponseEntity<?> all(){ return ResponseEntity.ok(service.all()); }
}
