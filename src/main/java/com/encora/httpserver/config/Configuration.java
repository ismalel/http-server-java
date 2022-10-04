package com.encora.httpserver.config;

import com.encora.httpserver.controller.UserController;
import com.encora.httpserver.repository.UserRepository;
import com.encora.httpserver.repository.UserRepositoryImpl;
import com.encora.httpserver.service.UserService;

public class Configuration {

    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl();
    private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);

    private static final UserController USER_CONTROLLER = new UserController(USER_SERVICE);

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static UserRepository getUserRepository() { return USER_REPOSITORY; }

    public static UserController getUserController() { return USER_CONTROLLER; }



}
