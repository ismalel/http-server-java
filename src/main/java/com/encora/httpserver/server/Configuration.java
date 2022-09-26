package com.encora.httpserver.server;

import com.encora.httpserver.data.user.UserRepository;
import com.encora.httpserver.domain.user.IUserRepository;
import com.encora.httpserver.domain.user.UserService;

public class Configuration {

    private static final IUserRepository USER_REPOSITORY = new UserRepository();
    private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);

    public static UserService getUserService() {
        return USER_SERVICE;
    }

    public static IUserRepository getUserRepository() { return USER_REPOSITORY; }



}
