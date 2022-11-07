package com.encora.httpserver.server;

import com.encora.httpserver.handler.BasicHandler;
import com.encora.httpserver.handler.UserHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static com.encora.httpserver.config.Configuration.getUserController;

public class ServerHttp {

    private final int PORT;

    public ServerHttp(int PORT) {
        this.PORT = PORT;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api", new BasicHandler());
        server.createContext("/user", new UserHandler(getUserController()));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server running on port: " + PORT);
    }
}
