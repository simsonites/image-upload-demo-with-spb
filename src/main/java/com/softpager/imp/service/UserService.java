package com.softpager.imp.service;

import com.softpager.imp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void save(User theUser);

    User getUser(long theId);
}
