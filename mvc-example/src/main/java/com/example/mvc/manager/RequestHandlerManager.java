package com.example.mvc.manager;

import com.example.mvc.controller.LoginController;
import com.example.mvc.controller.UserController;
import com.example.mvc.handler.RequestHandler;
import com.example.mvc.request.AddUserRequest;
import com.example.mvc.request.LoginRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestHandlerManager {

    private final Map<String, RequestHandler> handlerByUri =
        new ConcurrentHashMap<>();

    public RequestHandlerManager() {
        handlerByUri.put("/users/add", request ->
            BeanManager.getInstance()
                .getBeanOf(UserController.class)
                .usersAddPost((AddUserRequest) request)
        );
        handlerByUri.put("/login", request ->
            BeanManager.getInstance()
                .getBeanOf(LoginController.class)
                .loginPost((LoginRequest) request)
        );
    }

    @SuppressWarnings("unchecked")
    public <T> T handle(String uri, Object request) {
        RequestHandler handler = handlerByUri.get(uri);
        if (handler == null) {
            throw new IllegalStateException("not found handler");
        }
        return (T) handler.handleRequest(request);
    }
}
