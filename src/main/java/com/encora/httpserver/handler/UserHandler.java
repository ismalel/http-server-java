package com.encora.httpserver.handler;

import com.encora.httpserver.controller.Controller;
import com.encora.httpserver.controller.UserController;
import com.encora.httpserver.model.User;
import com.encora.httpserver.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class UserHandler implements HttpHandler {

    private final UserController controller;

    public UserHandler (UserController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                controller.getHandler(exchange);
                break;
            case "POST":
                controller.postHandler(exchange);
                break;
            case "PUT":
                controller.putHandler(exchange);
                break;
            case "DELETE":
                controller.deleteHandler(exchange);
                break;
            case "PATCH":
                controller.patchHandler(exchange);
                break;
            default:
                System.out.println("Method not allowed : " + exchange.getRequestMethod());
        }

    }



}
