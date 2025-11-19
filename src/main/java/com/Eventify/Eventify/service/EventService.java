package com.Eventify.Eventify.service;

import com.Eventify.Eventify.repository.*;

import com.Eventify.Eventify.Entity.*;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repo;
    private final UserRepository userRepo;


    public EventService(EventRepository repo, UserRepository userRepo){
        this.repo = repo;
        this.userRepo = userRepo;
    }


    public Event createEvent(String title, String desc, String loc, OffsetDateTime date, int capacity, Long organizerId){
        Optional<User> res = userRepo.findById(organizerId);

        if (res.isEmpty()){
            return null;
        }
        Event e = new Event();
        e.setTitle(title);
        e.setDescription(desc);
        e.setLocation(loc);
        e.setDateTime(date);
        e.setCapacity(capacity);
        e.setOrganizer(res.get());
        return repo.save(e);
    }


    public Event updateEvent(Long id, String title, String desc){
        Event e = repo.findById(id).orElseThrow();
        e.setTitle(title);
        e.setDescription(desc);
        return repo.save(e);
    }


    public void deleteEvent(Long id){ repo.deleteById(id); }


    public List<Event> all(){ return repo.findAll(); }
}