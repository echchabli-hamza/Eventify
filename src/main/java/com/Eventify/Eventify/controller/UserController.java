package com.Eventify.Eventify.controller;

import com.Eventify.Eventify.Entity.CustomUserDetails;
import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.service.RegistrationService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final RegistrationService regService;

    public UserController(RegistrationService regService) {
        this.regService = regService;
    }



    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<?> register(@PathVariable Long eventId, String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();
        return ResponseEntity.ok(regService.register(userId, eventId));
    }

    @GetMapping("/registrations")
    public ResponseEntity<?> myRegs(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails  = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok(regService.userRegistrations(userId));
    }
}
