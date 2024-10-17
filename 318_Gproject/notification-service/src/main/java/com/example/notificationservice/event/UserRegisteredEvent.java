package com.example.notificationservice.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisteredEvent {

    private Long userId;
    private Long eventId;

    // Default constructor (needed for Jackson deserialization)
    public UserRegisteredEvent() {
    }

    // Constructor for creating the event
    public UserRegisteredEvent(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    @JsonProperty("userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonProperty("eventId")
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
