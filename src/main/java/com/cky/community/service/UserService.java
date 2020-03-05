package com.cky.community.service;

import com.cky.community.entity.User;

public interface UserService {

    void userRegister(User user);

    User findById(int id);
}
