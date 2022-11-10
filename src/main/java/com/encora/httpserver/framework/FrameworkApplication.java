package com.encora.httpserver.framework;

import com.encora.httpserver.Application;
import com.encora.httpserver.framework.annotation.FrameworkApp;
import com.encora.httpserver.framework.context.ApplicationContext;
import com.encora.httpserver.framework.server.ServerHttp;
import com.encora.httpserver.framework.util.FrameworkLogger;

import java.io.IOException;
import java.util.logging.Logger;

public class FrameworkApplication {

    public static void run(Class<?> primarySource, String... args) {
        FrameworkApp frameworkApp = primarySource.getAnnotation(FrameworkApp.class);
        String[] packages = frameworkApp.scanBasePackages();

        ApplicationContext.init(packages);

        ServerHttp serverHttp = new ServerHttp(8080);
        try {
            serverHttp.start();
        } catch (IOException e) {
            FrameworkLogger.error("IOException : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

