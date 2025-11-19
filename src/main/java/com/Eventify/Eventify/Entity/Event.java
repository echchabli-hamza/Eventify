package com.Eventify.Eventify.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private String location;
    private OffsetDateTime dateTime;
    private Integer capacity;


    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;



}