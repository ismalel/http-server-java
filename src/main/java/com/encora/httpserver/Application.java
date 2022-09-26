package com.encora.httpserver;

import com.encora.httpserver.data.user.UserRepository;
import com.encora.httpserver.domain.user.IUserRepository;
import com.encora.httpserver.domain.user.User;
import com.encora.httpserver.domain.user.UserService;
import com.encora.httpserver.server.ServerHttp;

import java.io.IOException;
import java.util.UUID;

import static com.encora.httpserver.server.Configuration.getUserService;

public class Application {


    public static void main(String[] args) {
        /*ServerHttp serverHttp = new ServerHttp(8000);
        try {
            serverHttp.start();
        } catch (IOException e) {
            System.out.println("Error exception: " + e.getMessage());
            throw new RuntimeException(e);
        }*/
        UserService service = getUserService();
        User user = new User("", "ismalel", "123");
        String newUser = service.create(user);
        System.out.println("--- New user created --- " + newUser.toString());
        User getUser = service.getUser(newUser);
        System.out.println("--- Consult user  --- " + getUser.toString());

    }
}
