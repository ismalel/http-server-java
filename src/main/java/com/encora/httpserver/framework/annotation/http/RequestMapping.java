package com.encora.httpserver.framework.annotation.http;

import com.encora.httpserver.framework.util.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String name() default "";
    String[] value() default {};
    String path() default "";
    String[] params() default {};
    RequestMethod[] method() default {};
    String[] headers() default {};
    String[] consumes() default {};
    String[] produces() default {};
}
