package com.Eventify.Eventify.controller;

import com.Eventify.Eventify.Entity.CustomUserDetails;
import com.Eventify.Eventify.Entity.User;
import com.Eventify.Eventify.exception.UserNotFoundException;
import com.Eventify.Eventify.repository.UserRepository;
import com.Eventify.Eventify.service.EventService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private final EventService eventService;
    private final UserRepository userRepo;

    public OrganizerController(EventService eventService, UserRepository userRepo) {
        this.eventService = eventService;
        this.userRepo = userRepo;
    }

//    @PostMapping("/events")
//    public ResponseEntity<?> createEvent(@RequestBody Map<String,String> body) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails res = (CustomUserDetails) auth.getPrincipal();
//        Long userId = res.getId();
//        LocalDateTime localDateTime = LocalDateTime.parse(body.get("dateTime"));
//        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
//
//
//        return ResponseEntity.ok(
//                eventService.createEvent(
//                        body.get("title"),
//                        body.get("description"),
//                        body.get("location"),
//                       offsetDateTime,
//                        Integer.parseInt(body.get("capacity")),
//                        userId
//                )
//        );
//    }
@PostMapping("/events")
public ResponseEntity<?> createEvent(@RequestBody Map<String,String> body) {
    try {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails res = (CustomUserDetails) auth.getPrincipal();
        Long userId = res.getId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(body.get("dateTime"), formatter);

        // convert to OffsetDateTime in UTC
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
        return ResponseEntity.ok(
                eventService.createEvent(
                        body.get("title"),
                        body.get("description"),
                        body.get("location"),
                        offsetDateTime,
                        Integer.parseInt(body.get("capacity")),
                        userId
                )
        );
    } catch (Exception e) {
        e.printStackTrace(); // This will show the actual exception in the console
        return ResponseEntity.status(500).body(e.getMessage());
    }
}


    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Map<String,String> body) {
        return ResponseEntity.ok(eventService.updateEvent(id, body.get("title"), body.get("description")));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }
}

