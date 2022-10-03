package com.encora.httpserver.handler;

import com.encora.httpserver.model.User;
import com.encora.httpserver.service.UserService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class UserHandler implements Handler {

    private final UserService service;

    public UserHandler (UserService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                getHandler(exchange);
                break;
            case "POST":
                postHandler(exchange);
                break;
            case "PUT":
                putHandler(exchange);
                break;
            case "DELETE":
                deleteHandler(exchange);
                break;
            case "PATCH":
                patchHandler(exchange);
                break;
            default:
                System.out.println("Method not allowed : " + exchange.getRequestMethod());
        }

    }

    @Override
    public void getHandler(HttpExchange exchange) {
        System.out.println("asd" + exchange.getRequestURI().getPath());
        List<User> users = service.getUsers();
        users.add(new User("1123123", "ismael", "666"));
        try {
            if (users.isEmpty()) {
                String response = "There are not users registered yet";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String list = users.toString();
                exchange.sendResponseHeaders(200, list.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(list.getBytes());
                os.close();
            }
        } catch (IOException e) {
            System.out.println("IOException at UserHandler:getHandler : " + e.getMessage());
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

}
