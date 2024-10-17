package com.example.registrationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registrationId;

    private Long userId;
    private Long eventId;
    private String timestamp;

    // Default constructor
    public Registration() {}

    // Parameterized constructor
    public Registration(Long userId, Long eventId, String timestamp) {
        this.userId = userId;
        this.eventId = eventId;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
