package com.tvd12.example.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.io.IOException;
import java.time.Duration;

@Component
public class ChatSocketHandler implements WebSocketHandler {

    Sinks.Many<Event> sink = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    Flux<Event> outputMessages = sink.asFlux().cache(25);
    ObjectMapper mapper;
    Flux<String> output = Flux.from(outputMessages).map(this::toJson);


    public ChatSocketHandler(ObjectMapper objectMapper) {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .map(this::toEvent)
            .subscribe(
                event -> sink.tryEmitNext(event),
                throwable -> sink.tryEmitError(throwable)
            );

        return session.send(
            Mono.delay(Duration.ofMillis(100))
                .thenMany(output.map(session::textMessage))
                .log()
        );
    }

    Event toEvent(String json) {
        try {
            return mapper.readValue(json, Event.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON:" + json, e);
        }
    }

    String toJson(Event event) {
        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
