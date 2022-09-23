package com.encora.httpserver.server;

import com.encora.httpserver.handler.BasicHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerHttp {

    private int PORT;
    private HttpServer server;

    public ServerHttp(int PORT) {
        this.PORT = PORT;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/applications/myapp", new BasicHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server running on port: " + PORT);
    }
}
