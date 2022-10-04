package com.encora.httpserver.controller;

import com.encora.httpserver.model.User;
import com.encora.httpserver.service.UserService;
import com.encora.httpserver.util.HttpUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController implements Controller {

    private final UserService service;


    public UserController (UserService service) {
        this.service = service;
    }

    @Override
    public void getHandler(HttpExchange exchange) throws IOException {
        Map<String, List<String>> params = HttpUtils.splitQuery(exchange);

        if (params.isEmpty()) {
            sendResponse(exchange, 200, HttpUtils.userListToJSON(service.getUsers()));
        } else {
            User user = service.getUser(params.get("id").toString());
            if(user != null) {
                sendResponse(exchange, 200, user.toString());
            } else {
                sendResponse(exchange, 404, "User not found");
            }
        }
    }

    @Override
    public void postHandler(HttpExchange exchange) {

    }

    @Override
    public void putHandler(HttpExchange exchange) {

    }

    @Override
    public void patchHandler(HttpExchange exchange) {

    }

    @Override
    public void deleteHandler(HttpExchange exchange) {

    }

    private void sendResponse(HttpExchange exchange, int code, String response) throws IOException {
        exchange.sendResponseHeaders(code, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

}
