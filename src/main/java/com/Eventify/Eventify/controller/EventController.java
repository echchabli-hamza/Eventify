package com.Eventify.Eventify.controller;

import com.Eventify.Eventify.Entity.Event;
import com.Eventify.Eventify.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;
    public EventController(EventService service){ this.service = service; }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,String> body){
        Event e = service.createEvent(
                body.get("title"),
                body.get("description"),
                body.get("location"),
                OffsetDateTime.parse(body.get("dateTime")),
                Integer.parseInt(body.get("capacity")),
                Long.parseLong(body.get("organizerId"))
        );
        return ResponseEntity.ok(e);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String,String> body){
        return ResponseEntity.ok(service.updateEvent(id, body.get("title"), body.get("description")));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteEvent(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }


    @GetMapping
    public ResponseEntity<?> all(){ return ResponseEntity.ok(service.all()); }
}
