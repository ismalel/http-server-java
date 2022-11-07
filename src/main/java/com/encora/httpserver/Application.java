package com.encora.httpserver;

import com.encora.httpserver.model.User;
import com.encora.httpserver.server.ServerHttp;
import com.encora.httpserver.util.JSON;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
       // System.out.println(JSON.toJSON(new User("1","ismael", "123")));
      ServerHttp serverHttp = new ServerHttp(8000);
        try {
            serverHttp.start();
        } catch (IOException e) {
            System.out.println("Error exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
