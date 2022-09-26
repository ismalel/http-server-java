package com.encora.httpserver.domain.user;

public interface IUserRepository {

    String createUser(User user);
    User findUserById(String id);
}
