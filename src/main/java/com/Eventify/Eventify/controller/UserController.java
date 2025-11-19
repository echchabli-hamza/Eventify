package com.Eventify.Eventify.controller;

import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.exception.UserNotFoundException;
import com.Eventify.Eventify.repository.UserRepository;
import com.Eventify.Eventify.service.RegistrationService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final RegistrationService regService;
    private final UserRepository userRepo;

    public UserController(RegistrationService regService, UserRepository userRepo) {
        this.regService = regService;
        this.userRepo = userRepo;
    }



    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<?> register(@PathVariable Long eventId, String email) {
        Long userId = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
                .getId();
        return ResponseEntity.ok(regService.register(userId, eventId));
    }

    @GetMapping("/registrations")
    public ResponseEntity<?> myRegs(String email) {
        Long userId = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
                .getId();
        return ResponseEntity.ok(regService.userRegistrations(userId));
    }
}
