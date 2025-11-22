package com.Eventify.Eventify.controller;





import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final EventService eventService;

    public AdminController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        return ResponseEntity.ok(userService.all());
    }

    @PutMapping("/users/{id}/role/{role}")
    public ResponseEntity<?> changeRole(@PathVariable("id") Long id, @PathVariable("role") String role) {
        return ResponseEntity.ok(userService.updateRole(id, role));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> adminDeleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(Map.of("adminDeleted", true));
    }
}

