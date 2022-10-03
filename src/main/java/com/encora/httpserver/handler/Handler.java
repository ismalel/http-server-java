package com.encora.httpserver.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;

public interface Handler extends HttpHandler {
    public void getHandler(HttpExchange exchange);
    public void postHandler(HttpExchange exchange);
    public void putHandler(HttpExchange exchange);
    public void patchHandler(HttpExchange exchange);
    public void deleteHandler(HttpExchange exchange);
}
