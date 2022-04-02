package com.example.mvc.converter;

import com.example.mvc.entity.AccessToken;
import com.example.mvc.entity.User;
import com.example.mvc.model.AccessTokenModel;
import com.example.mvc.model.UserModel;

public class EntityToModelConverter {

    public UserModel toModel(User entity) {
        if (entity == null) {
            return null;
        }
        return new UserModel(
            entity.getId(),
            entity.getUsername(),
            entity.getPassword()
        );
    }

    public AccessTokenModel toModel(AccessToken entity) {
        if (entity == null) {
            return null;
        }
        return new AccessTokenModel(
            entity.getAccessToken(),
            entity.getExpiredTime()
        );
    }
}
