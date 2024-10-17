package com.example.notificationservice.service;

import com.example.notificationservice.event.UserRegisteredEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson object mapper

    @Bean
    public Consumer<Message<byte[]>> userRegistered() {
        return message -> {
            try {
                // Log the raw payload for debugging
                String rawPayload = new String(message.getPayload());
                logger.info("Raw payload received from Kafka: {}", rawPayload);

                // Manually deserialize the message payload from byte[] to UserRegisteredEvent
                UserRegisteredEvent event = objectMapper.readValue(rawPayload, UserRegisteredEvent.class);

                // Log the event when it's successfully deserialized
                logger.info("UserRegisteredEvent deserialized: userId={}, eventId={}", event.getUserId(), event.getEventId());

                // Process the event (e.g., send notification)
                System.out.println("User " + event.getUserId() + " registered for event " + event.getEventId());
            } catch (Exception e) {
                // Log the error if deserialization fails
                logger.error("Failed to deserialize UserRegisteredEvent", e);
            }
        };
    }
}
