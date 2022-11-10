package com.encora.httpserver.framework.server;

import com.encora.httpserver.framework.context.ApplicationContext;
import com.encora.httpserver.framework.handler.BasicHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Map;

public class ServerHttp {

    private final int PORT;
    private final Map<String, Method> methods = ApplicationContext.getControllersMethods();
    private final BasicHandler basicHandler = new BasicHandler();
    public ServerHttp(int PORT) {
        this.PORT = PORT;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        // REFACTOR THIS CODE ASAP
        for (Map.Entry<String, Method> method : methods.entrySet()) {
            String[] p = method.getKey().split("/");
            String path = "/" + p[1] + "/" + p[2];
            server.createContext(path, basicHandler);
         }
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server running on port: " + PORT);
    }
}
