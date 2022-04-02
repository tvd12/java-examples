package com.example.mvc.converter;

import com.example.mvc.model.AddUserModel;
import com.example.mvc.model.AuthenticationModel;
import com.example.mvc.request.AddUserRequest;
import com.example.mvc.request.LoginRequest;

public class RequestToModelConverter {

    public AuthenticationModel toModel(LoginRequest request) {
        return new AuthenticationModel(
            request.getUsername(),
            request.getPassword()
        );
    }

    public AddUserModel toModel(AddUserRequest request) {
        return new AddUserModel(
            request.getUsername(),
            request.getPassword()
        );
    }
}
