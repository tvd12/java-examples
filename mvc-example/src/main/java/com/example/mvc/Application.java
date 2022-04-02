package com.example.mvc;

import com.example.mvc.controller.LoginController;
import com.example.mvc.controller.UserController;
import com.example.mvc.converter.EntityToModelConverter;
import com.example.mvc.converter.ModelToEntityConverter;
import com.example.mvc.converter.ModelToResponseConverter;
import com.example.mvc.converter.RequestToModelConverter;
import com.example.mvc.manager.BeanManager;
import com.example.mvc.manager.RequestHandlerManager;
import com.example.mvc.repo.AccessTokenRepository;
import com.example.mvc.repo.UserRepository;
import com.example.mvc.request.AddUserRequest;
import com.example.mvc.request.LoginRequest;
import com.example.mvc.response.AddUserResponse;
import com.example.mvc.response.LoginResponse;
import com.example.mvc.service.AuthenticationService;
import com.example.mvc.service.UserService;
import com.example.mvc.validator.LoginValidator;
import com.example.mvc.validator.UserValidator;

public class Application {

    public static void main(String[] args) {
        BeanManager beanManager = BeanManager.getInstance();
        beanManager.addBean(
            AccessTokenRepository.class,
            new AccessTokenRepository()
        );
        beanManager.addBean(
            UserRepository.class,
            new UserRepository()
        );

        beanManager.addBean(
            EntityToModelConverter.class,
            new EntityToModelConverter()
        );
        beanManager.addBean(
            ModelToResponseConverter.class,
            new ModelToResponseConverter()
        );
        beanManager.addBean(
            RequestToModelConverter.class,
            new RequestToModelConverter()
        );
        beanManager.addBean(
            ModelToEntityConverter.class,
            new ModelToEntityConverter()
        );

        beanManager.addBean(
            UserService.class,
            new UserService()
        );
        beanManager.addBean(
            AuthenticationService.class,
            new AuthenticationService()
        );

        beanManager.addBean(
            LoginValidator.class,
            new LoginValidator()
        );
        beanManager.addBean(
            UserValidator.class,
            new UserValidator()
        );

        beanManager.addBean(
            LoginController.class,
            new LoginController()
        );
        beanManager.addBean(
            UserController.class,
            new UserController()
        );

        RequestHandlerManager requestHandlerManager = new RequestHandlerManager();

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("dungtv");
        addUserRequest.setPassword("123456799");
        AddUserResponse addUserResponse = requestHandlerManager.handle(
            "/users/add",
            addUserRequest
        );
        System.out.println(addUserResponse);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("dungtv");
        loginRequest.setPassword("123456799");
        LoginResponse loginResponse = requestHandlerManager.handle(
            "/login",
            loginRequest
        );
        System.out.println(loginResponse);
    }
}
