package com.tvd12.example.websocket;

import java.net.URI;
import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactiveJavaClientWebSocket {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; ++i) {
            WebSocketClient client = new ReactorNettyWebSocketClient();
            client.execute(
                    URI.create("ws://localhost:8080/event-emitter"),
                    ReactiveJavaClientWebSocket::senMessage
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
                    session.textMessage("event-spring-reactive-client-websocket"))
            )
            .thenMany(
                session.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .log()
            )
            .then();
    }
}
