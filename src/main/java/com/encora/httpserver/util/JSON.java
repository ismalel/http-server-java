package com.encora.httpserver.util;

import com.encora.httpserver.model.Cat;
import com.encora.httpserver.model.User;
import com.encora.httpserver.util.annotation.JsonField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JSON {

    private static Logger LOG = Logger.getLogger("JSON");

    public static String toJSON(Object obj) throws IllegalAccessException {
        Class<?> clase = obj.getClass();
        Field[] campos = clase.getDeclaredFields();
        StringBuilder json = new StringBuilder();
        String value;
        json.append("{");
        for (int i = 0; i <= campos.length - 1; i++) {
            campos[i].setAccessible(true);
            JsonField jsonField = campos[i].getAnnotation(JsonField.class);
            value = campos[i].get(obj).toString();
            if (campos[i].getType().getSimpleName().equals("String")) {
                value =  value + "\"";
            }
            json.append("\"" + jsonField.fieldName() + "\":" + value  + (i < campos.length - 1 ? "," : ""));
        }
        json.append("}");
        return json.toString();
    }

    public static <T> T fromJSON(String json, Class<T> clase) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String j = json.substring(1, json.length() - 1);
        String[] r = j.split("\"");
        String[] values = j.split(",");

        Constructor<?> objectConstructor = Class.forName(clase.getName()).getConstructor(null);
        Object object = objectConstructor.newInstance(null);
        Field[] fields = clase.getDeclaredFields();

        for (int i = 0; i <= fields.length - 1; i++) {
            fields[i].setAccessible(true);
            String[] data = values[i].split(":");

            switch (fields[i].getType().getSimpleName()) {
                case "Long":
                    Long value = Long.valueOf(data[1]);
                    fields[i].set(object, value);
                    break;
                case "String":
                    String stringValue = data[1].substring(1, data[1].length()-1);
                    fields[i].set(object, stringValue);
                    break;
            }

        }

        return clase.cast(object);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        String jsonUser = "{\"id\" :1406861,\"name\" : \"Javier\",\"password\" : \"yF&u7z?jfZ)WHC3\"}";
        String jsonCat = "{\"id\" :000023,\"name\" : \"Bola de Nieve\",\"breed\" : \"Europeo\"}";

        User a = JSON.fromJSON(jsonUser, User.class);
        Cat c = JSON.fromJSON(jsonCat, Cat.class);
        String jsonx = JSON.toJSON(a);
        LOG.info(jsonx);
        LOG.info("User: " + JSON.fromJSON(jsonx, User.class));

    }
}
