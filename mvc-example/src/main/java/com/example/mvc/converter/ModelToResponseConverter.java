package com.example.mvc.converter;

import com.example.mvc.model.AccessTokenModel;
import com.example.mvc.response.LoginResponse;

public class ModelToResponseConverter {

    public LoginResponse toResponse(AccessTokenModel model) {
        return new LoginResponse(
            model.getAccessToken()
        );
    }
}
