package com.Eventify.Eventify.repository;


import com.Eventify.Eventify.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.cert.Extension;


@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    User findByEmail(String name);
}
