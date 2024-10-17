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

    // Get all registrations for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Registration>> getRegistrationsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(registrationService.getRegistrationsByUserId(userId), HttpStatus.OK);
    }

    // Get all registrations for a specific event
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Registration>> getRegistrationsByEventId(@PathVariable Long eventId) {
        return new ResponseEntity<>(registrationService.getRegistrationsByEventId(eventId), HttpStatus.OK);
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
