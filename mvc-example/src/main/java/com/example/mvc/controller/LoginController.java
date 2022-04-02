package com.example.mvc.controller;

import com.example.mvc.converter.ModelToResponseConverter;
import com.example.mvc.converter.RequestToModelConverter;
import com.example.mvc.manager.BeanManager;
import com.example.mvc.model.AccessTokenModel;
import com.example.mvc.model.AuthenticationModel;
import com.example.mvc.request.LoginRequest;
import com.example.mvc.response.LoginResponse;
import com.example.mvc.service.AuthenticationService;
import com.example.mvc.validator.LoginValidator;

public class LoginController extends AbstractController {

    private final AuthenticationService authenticationService =
        getBean(AuthenticationService.class);
    private final LoginValidator loginValidator =
        getBean(LoginValidator.class);
    private final RequestToModelConverter requestToModelConverter =
        getBean(RequestToModelConverter.class);
    private final ModelToResponseConverter modelToResponseConverter =
        getBean(ModelToResponseConverter.class);

    public LoginResponse loginPost(LoginRequest request) {
        loginValidator.validate(request);
        AuthenticationModel authenticationModel = requestToModelConverter
            .toModel(request);
        AccessTokenModel accessTokenModel = authenticationService
            .authenticate(authenticationModel);
        return modelToResponseConverter.toResponse(accessTokenModel);
    }
}
