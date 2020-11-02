package com.example.rsvps;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
class RsvpsWebSocketHandler extends AbstractWebSocketHandler {
    private static final Logger logger =
            Logger.getLogger(RsvpsWebSocketHandler.class.getName());

    private final RsvpsKafkaProducer rsvpsKafkaProducer;

    public RsvpsWebSocketHandler(RsvpsKafkaProducer rsvpsKafkaProducer) {
        this.rsvpsKafkaProducer = rsvpsKafkaProducer;
    }

    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message) {
        //System.out.println(message.getPayload());
        logger.log( Level.INFO, "New RSVP:\n {0}", message.getPayload());

        rsvpsKafkaProducer.sendRsvpMessage(message);
    }
}
