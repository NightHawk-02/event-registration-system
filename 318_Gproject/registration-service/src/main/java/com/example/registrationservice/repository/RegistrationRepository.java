package com.example.registrationservice.repository;

import com.example.registrationservice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // Custom method to find registrations by userId
    List<Registration> findByUserId(Long userId);

    // Custom method to find registrations by eventId
    List<Registration> findByEventId(Long eventId);
}
