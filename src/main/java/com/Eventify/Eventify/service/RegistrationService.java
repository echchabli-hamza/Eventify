package com.Eventify.Eventify.service;

import com.Eventify.Eventify.Entity.*;
import com.Eventify.Eventify.repository.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository repo;
    private final UserRepository userRepo;
    private final EventRepository eventRepo;


    public RegistrationService(RegistrationRepository repo, UserRepository userRepo, EventRepository eventRepo){
        this.repo = repo;
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
    }


    public Registration register(Long userId, Long eventId){
        User u = userRepo.findById(userId).orElseThrow();
        Event e = eventRepo.findById(eventId).orElseThrow();


        Registration r = new Registration();
        r.setUser(u);
        r.setEvent(e);
        r.setRegisteredAt(OffsetDateTime.now());
        r.setStatus("REGISTERED");
        return repo.save(r);
    }


    public List<Registration> userRegistrations(Long userId){
        User u = userRepo.findById(userId).orElseThrow();
        return repo.findByUser(u);
    }
}