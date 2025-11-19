package com.Eventify.Eventify.controller;


import com.Eventify.Eventify.Entity.Registration;
import com.Eventify.Eventify.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    private final RegistrationService service;
    public RegistrationController(RegistrationService service){ this.service = service; }


    @PostMapping
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        Registration r = service.register(Long.parseLong(body.get("userId")), Long.parseLong(body.get("eventId")));
        return ResponseEntity.ok(r);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> regUser(@PathVariable Long id){ return ResponseEntity.ok(service.userRegistrations(id)); }
}