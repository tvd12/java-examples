package com.example.mvc.service;

import com.example.mvc.converter.EntityToModelConverter;
import com.example.mvc.converter.ModelToEntityConverter;
import com.example.mvc.entity.AccessToken;
import com.example.mvc.entity.User;
import com.example.mvc.manager.BeanManager;
import com.example.mvc.model.AccessTokenModel;
import com.example.mvc.model.AddUserModel;
import com.example.mvc.model.UserModel;
import com.example.mvc.repo.AccessTokenRepository;
import com.example.mvc.repo.UserRepository;

import java.util.UUID;

public class UserService {

    private final UserRepository userRepository = BeanManager.getInstance()
        .getBean(UserRepository.class);
    private final AccessTokenRepository accessTokenRepository =
        BeanManager.getInstance()
            .getBean(AccessTokenRepository.class);
    private final EntityToModelConverter entityToModelConverter =
        BeanManager.getInstance()
            .getBean(EntityToModelConverter.class);
    private final ModelToEntityConverter modelToEntityConverter =
        BeanManager.getInstance()
            .getBean(ModelToEntityConverter.class);

    private static final long TOKEN_PERIOD_TIME_7_DAY = 7 * 24 * 60 * 60 * 1000;

    public long saveUser(AddUserModel model) {
        User user = modelToEntityConverter.toEntity(model);
        userRepository.save(user);
        return user.getId();
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsernameOptional(
            username
        )
            .map(entityToModelConverter::toModel)
            .orElse(null);
    }

    public AccessTokenModel generateAndSaveAccessToken(
        long userId
    ) {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(generateAccessToken());
        accessToken.setUserId(userId);
        accessToken.setExpiredTime(
            System.currentTimeMillis() + TOKEN_PERIOD_TIME_7_DAY
        );
        accessTokenRepository.save(accessToken);
        return entityToModelConverter.toModel(accessToken);
    }

    private String generateAccessToken() {
        return UUID.randomUUID().toString();
    }
}
