package com.example.mvc.service;

import com.example.mvc.exception.AuthenticationException;
import com.example.mvc.manager.BeanManager;
import com.example.mvc.model.AccessTokenModel;
import com.example.mvc.model.AuthenticationModel;
import com.example.mvc.model.UserModel;

public class AuthenticationService {

    private final UserService userService = BeanManager.getInstance()
        .getBean(UserService.class);

    public AccessTokenModel authenticate(AuthenticationModel model) {
        UserModel userModel = userService.getUserByUsername(
            model.getUsername()
        );
        if (!userModel.getPassword().equals(model.getPassword())) {
            throw new AuthenticationException();
        }
        return userService.generateAndSaveAccessToken(
            userModel.getUserId()
        );
    }
}
