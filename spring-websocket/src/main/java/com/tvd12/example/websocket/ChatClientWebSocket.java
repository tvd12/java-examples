package com.tvd12.example.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;

public class ChatClientWebSocket {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; ++i) {
            WebSocketClient client = new ReactorNettyWebSocketClient();
            client.execute(
                    URI.create("ws://localhost:8080/chat"),
                    ChatClientWebSocket::senMessage
                )
                .subscribe();
        }
        while (true) {
            Thread.sleep(5);
        }
    }

    private static Mono<Void> senMessage(WebSocketSession session) {
        return session.send(
                Mono.just(
                    session.textMessage(
                        serializeObject(
                            new Event("event hello", "message world")
                        )
                    )
                )
            )
            .thenMany(
                session.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .log()
            )
            .then();
    }

    private static String serializeObject(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
