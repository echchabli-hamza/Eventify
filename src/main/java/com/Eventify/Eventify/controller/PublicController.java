package com.Eventify.Eventify.controller;


import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final UserService userService;
    private final EventService eventService;

    public PublicController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    // Registration
    @PostMapping("/users")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(
                userService.createUser(
                        body.get("name"),
                        body.get("email"),
                        body.get("password")
                )
        );
    }

    // Public events list
    @GetMapping("/events")
    public ResponseEntity<?> listEvents() {
        return ResponseEntity.ok(eventService.all());
    }
}

