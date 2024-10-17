package com.example.registrationservice.service;

import com.example.registrationservice.event.UserRegisteredEvent;
import com.example.registrationservice.model.Registration;
import com.example.registrationservice.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StreamBridge streamBridge;

    // Create a new registration and publish the UserRegisteredEvent
    public Registration createRegistration(Long userId, Long eventId) {
        logger.info("Attempting to create registration for user {} and event {}", userId, eventId);

        // Create a new registration object
        Registration registration = new Registration(userId, eventId, LocalDateTime.now().toString());
        Registration savedRegistration = registrationRepository.save(registration);

        // Log the registration creation
        logger.info("Registration created successfully with ID {}", savedRegistration.getRegistrationId());

        // Publish the event to the Kafka topic "user-registered"
        UserRegisteredEvent event = new UserRegisteredEvent(userId, eventId);

        // Log before publishing the event
        logger.info("Publishing UserRegisteredEvent for user {} and event {}", userId, eventId);

        boolean eventSent = streamBridge.send("userRegistered-out-0", event);

        // Log after the event is published
        if (eventSent) {
            logger.info("UserRegisteredEvent published successfully for user {} and event {}", userId, eventId);
        } else {
            logger.error("Failed to publish UserRegisteredEvent for user {} and event {}", userId, eventId);
        }

        return savedRegistration;
    }

    // Get all registrations for a specific user
    public List<Registration> getRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    // Get all registrations for a specific event
    public List<Registration> getRegistrationsByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    // Get a specific registration by ID
    public Registration getRegistrationById(Long registrationId) {
        return registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    // Delete a registration by its ID
    public void deleteRegistration(Long registrationId) {
        registrationRepository.deleteById(registrationId);
    }
}
