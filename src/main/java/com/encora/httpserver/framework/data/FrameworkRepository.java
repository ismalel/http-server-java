package com.encora.httpserver.framework.data;

import java.util.List;

public interface FrameworkRepository<T, ID> {

    List<T> findAll();
    T getById(ID id);
    T save(T t);

}
