package com.example.registrationservice.controller;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // Create a new registration
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestParam Long userId, @RequestParam Long eventId) {
        return new ResponseEntity<>(registrationService.createRegistration(userId, eventId), HttpStatus.CREATED);
    }

    // Get all registrations, filtered by userId or eventId using query parameters
    @GetMapping
    public ResponseEntity<List<Registration>> getRegistrations(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long eventId) {

        if (userId != null) {
            return new ResponseEntity<>(registrationService.getRegistrationsByUserId(userId), HttpStatus.OK);
        } else if (eventId != null) {
            return new ResponseEntity<>(registrationService.getRegistrationsByEventId(eventId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if no query parameters are provided
        }
    }

    // Get a specific registration by ID
    @GetMapping("/{registrationId}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long registrationId) {
        return new ResponseEntity<>(registrationService.getRegistrationById(registrationId), HttpStatus.OK);
    }

    // Cancel a registration (delete by ID)
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long registrationId) {
        registrationService.deleteRegistration(registrationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
