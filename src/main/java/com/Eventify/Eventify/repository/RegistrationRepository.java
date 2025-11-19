package com.Eventify.Eventify.repository;

import com.Eventify.Eventify.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;




public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    List<Registration> findByEvent(Event event);
}