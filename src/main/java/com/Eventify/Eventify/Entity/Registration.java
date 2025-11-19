package com.Eventify.Eventify.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "registrations")
@Data
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne @JoinColumn(name = "event_id")
    private Event event;


    private OffsetDateTime registeredAt;
    private String status;



}
