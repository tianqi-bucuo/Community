package com.cky.community.service;

import com.cky.community.entity.User;

public interface UserService {

    void userRegister(String userName, String password);

    User findById(int id);

    User login(String userName,String password);

    void registerWithAvatar(String userName, String password, String avatar);
}
