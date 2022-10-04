package com.encora.httpserver.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public interface Controller {
    public void getHandler(HttpExchange exchange) throws IOException;
    public void postHandler(HttpExchange exchange);
    public void putHandler(HttpExchange exchange);
    public void patchHandler(HttpExchange exchange);
    public void deleteHandler(HttpExchange exchange);
}
