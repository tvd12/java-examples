package com.example.mvc.controller;

import com.example.mvc.converter.ModelToResponseConverter;
import com.example.mvc.converter.RequestToModelConverter;
import com.example.mvc.manager.BeanManager;
import com.example.mvc.model.AddUserModel;
import com.example.mvc.request.AddUserRequest;
import com.example.mvc.response.AddUserResponse;
import com.example.mvc.service.UserService;
import com.example.mvc.validator.UserValidator;

public class UserController extends AbstractController {

    private final UserService userService = BeanManager.getInstance()
        .getBean(UserService.class);
    private final UserValidator userValidator = BeanManager.getInstance()
        .getBean(UserValidator.class);
    private final RequestToModelConverter requestToModelConverter =
        getBean(RequestToModelConverter.class);
    private final ModelToResponseConverter modelToResponseConverter =
        getBean(ModelToResponseConverter.class);

    public AddUserResponse usersAddPost(
        AddUserRequest request
    ) {
        userValidator.validate(request);
        AddUserModel model = requestToModelConverter.toModel(request);
        long userId = userService.saveUser(model);
        return new AddUserResponse(userId);
    }
}
