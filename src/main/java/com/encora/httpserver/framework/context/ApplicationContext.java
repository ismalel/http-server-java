package com.encora.httpserver.framework.context;

import com.encora.httpserver.framework.annotation.Controller;
import com.encora.httpserver.framework.annotation.Repository;
import com.encora.httpserver.framework.annotation.Service;
import com.encora.httpserver.framework.annotation.http.GetMapping;
import com.encora.httpserver.framework.annotation.http.PostMapping;
import com.encora.httpserver.framework.annotation.http.RequestMapping;
import com.encora.httpserver.framework.util.FrameworkLogger;
import com.encora.httpserver.framework.util.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public final class ApplicationContext {

    private static final Map<Class, Object> beans = new HashMap<>();

    public static void init(String[] packageNames) {

        for(String packageName : packageNames) {
            Class[] classes = getClasses(packageName);
            for (Class c : classes) {
                if (c.isAnnotationPresent(Controller.class) || c.isAnnotationPresent(Service.class) || c.isAnnotationPresent(Repository.class)) {
                    Object object = null;
                    try {
                        Constructor<?> objectConstructor = Class.forName(c.getName()).getConstructor(null);
                        object = objectConstructor.newInstance(null);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                             InvocationTargetException | NoSuchMethodException ex) {
                        FrameworkLogger.error("Exception: " + ex.getMessage());
                    }
                    beans.put(c,object);
                }
            }
        }

        FrameworkLogger.info(String.valueOf(beans.size()));
    }

    public static <T> T getBean(Class<?> clazz) {
        return (T) beans.get(clazz);
    }

    public static Class[] getClasses(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        ArrayList<Class> classes = new ArrayList<Class>();
        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (IOException | ClassNotFoundException e) {
            FrameworkLogger.error("Exception: " + e.getMessage());
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static Map<String, Class> getControllers() {
        Map<String, Class> controllers = new HashMap<>();
        for (Map.Entry<Class, Object> entry : beans.entrySet()) {
            if (entry.getKey().isAnnotationPresent(Controller.class)) {
                controllers.put(entry.getKey().getSimpleName(), entry.getValue().getClass());
            }
        }
        return controllers;
    }

    public static Map<String, Method> getControllersMethods() {
        Map<String, Class> controllers = getControllers();
        Map<String, Method> methods = new HashMap<>();
        for (Map.Entry<String, Class> entry : controllers.entrySet()) {
            RequestMapping requestMapping = (RequestMapping) entry.getValue().getAnnotation(RequestMapping.class);
            Method[] methodz = entry.getValue().getMethods();
            for (Method m : methodz) {
                String key = requestMapping.path();
                if(m.isAnnotationPresent(PostMapping.class)) {
                    PostMapping postMapping = m.getAnnotation(PostMapping.class);
                    key = RequestMethod.POST + key + postMapping.path();
                    methods.put(key, m);
                }
                if(m.isAnnotationPresent(GetMapping.class)) {
                    GetMapping getMapping = m.getAnnotation(GetMapping.class);
                    key = RequestMethod.GET + key + getMapping.path();
                    methods.put(key, m);
                }
            }
        }
        return methods;
    }
}
