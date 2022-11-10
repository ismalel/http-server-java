package com.encora.httpserver;

import com.encora.httpserver.controller.UserController;
import com.encora.httpserver.framework.FrameworkApplication;
import com.encora.httpserver.framework.annotation.FrameworkApp;
import com.encora.httpserver.framework.context.ApplicationContext;
import com.encora.httpserver.framework.server.ServerHttp;
import com.encora.httpserver.framework.util.FrameworkLogger;

import java.io.IOException;

@FrameworkApp(scanBasePackages = {"com.encora.httpserver.controller","com.encora.httpserver.service","com.encora.httpserver.repository" })
public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FrameworkApplication.run(Application.class, args);
    }
}
