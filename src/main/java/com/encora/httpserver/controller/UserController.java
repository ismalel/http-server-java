package com.encora.httpserver.controller;

import com.encora.httpserver.framework.annotation.AutoWired;
import com.encora.httpserver.framework.annotation.http.GetMapping;
import com.encora.httpserver.framework.annotation.http.PostMapping;
import com.encora.httpserver.framework.annotation.http.RequestMapping;
import com.encora.httpserver.framework.util.FrameworkLogger;
import com.encora.httpserver.model.User;
import com.encora.httpserver.service.UserService;
import com.encora.httpserver.framework.annotation.Controller;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping(path = "/api")
public class UserController {


    //Controller methods
    @GetMapping(path = "/user")
    public User user() {
        User user = new User(1230L, "Ismael", "yF&u7z?jfZ)WHC3");
        return user;
    }

    @PostMapping(path = "/user")
    public User postUser(User user){
        FrameworkLogger.info("UserController: " + user.toString());
        return user;
    }

}
