package com.encora.httpserver;

import com.encora.httpserver.server.ServerHttp;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        ServerHttp serverHttp = new ServerHttp(8000);
        try {
            serverHttp.start();
        } catch (IOException e) {
            System.out.println("Error exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
