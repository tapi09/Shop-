package com.completeapplication.shop.service;

import com.completeapplication.shop.model.User;

import java.util.List;


public interface UserService {

    User saveUser(User user);
    User getUser(Integer id);
    List<User> getUsers();

    void updateEntityIfExists(Integer id, User user);
}
