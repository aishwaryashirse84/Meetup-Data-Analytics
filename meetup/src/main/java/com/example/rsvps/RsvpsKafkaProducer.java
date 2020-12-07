package com.example.rsvps;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;

@Component
//here,we need a source bcoz we are actually writting a producer for kafka.
@EnableBinding(Source.class)
public class RsvpsKafkaProducer {

    private static final int SENDING_MESSAGE_TIMEOUT_MS = 10000;

    private final Source source;
//constructor
//in order to obtain a souce object,we just take an advantage of spring dependency injection via class constructor.
    public RsvpsKafkaProducer(Source source) {
        this.source = source;
    }
//following method will send the collected data to kafka via the source object.
    public void sendRsvpMessage(WebSocketMessage<?> message) {

        source.output()
                .send(MessageBuilder.withPayload(message.getPayload())
                                .build(),
                        SENDING_MESSAGE_TIMEOUT_MS);
    }
}