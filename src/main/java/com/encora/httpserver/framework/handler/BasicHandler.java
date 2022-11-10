package com.encora.httpserver.framework.handler;

import com.encora.httpserver.framework.context.ApplicationContext;
import com.encora.httpserver.framework.util.FrameworkLogger;
import com.encora.httpserver.framework.util.JSON;
import com.encora.httpserver.framework.util.RequestMethod;
import com.encora.httpserver.model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

public class BasicHandler implements HttpHandler {

    private Map<String, Class> controllers = ApplicationContext.getControllers();
    private Map<String, Method> methods = ApplicationContext.getControllersMethods();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        RequestMethod requestMethod = RequestMethod.valueOf(exchange.getRequestMethod());
        String path = exchange.getRequestURI().getPath();
        FrameworkLogger.info(requestMethod + path);

        Method method = methods.get(requestMethod + path);
        Class clazz = method.getDeclaringClass();
        Object controller = ApplicationContext.getBean(clazz);
        handle(exchange, controller, method);

    }

    public void handle(HttpExchange exchange, Object controller, Method method) {
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "GET":
                try {
                    Object object = method.invoke(controller);
                    String json = JSON.toJSON(object);
                    exchange.sendResponseHeaders(200, json.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(json.getBytes());
                    os.close();
                    FrameworkLogger.info(json);
                } catch (Exception ex) {
                    FrameworkLogger.error("Excepcion: " + ex.getMessage());
                }
                break;
            case "POST":
                try {
                    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);
                    int b;
                    StringBuilder buf = new StringBuilder(512);
                    while ((b = br.read()) != -1) {
                        buf.append((char) b);
                    }
                    br.close();
                    isr.close();
                    Object parameter = JSON.fromJSON(buf.toString(), User.class); // HOW TO GET MODEL TYPE CLASS
                    Object object = method.invoke(controller, parameter);
                    String json = JSON.toJSON(object);
                    exchange.sendResponseHeaders(200, json.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(json.getBytes());
                    os.close();
                    FrameworkLogger.info(json);
                    FrameworkLogger.info("HAndler: " + object.toString());
                } catch (Exception e) {
                    FrameworkLogger.error("Exception: " + e.getMessage());
                }

                break;
        }
    }


    /*
     ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("index.html").getFile());
        exchange.sendResponseHeaders(200,file.length());
        OutputStream os = exchange.getResponseBody();
        Files.copy(file.toPath(), os);
        os.close();
     */

    /*
     InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();
        FrameworkLogger.info(buf.toString());
     */
}
