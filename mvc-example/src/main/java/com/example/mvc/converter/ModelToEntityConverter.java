package com.example.mvc.converter;

import com.example.mvc.entity.User;
import com.example.mvc.model.AddUserModel;

public class ModelToEntityConverter {

    public User toEntity(AddUserModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        return user;
    }
}
