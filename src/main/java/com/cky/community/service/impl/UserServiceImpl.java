package com.cky.community.service.impl;

import com.cky.community.dao.UserMapper;
import com.cky.community.entity.User;
import com.cky.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void userRegister(String userName,String password) {
        userMapper.create(userName, password);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName, password);
    }

    @Override
    public void registerWithAvatar(String userName, String password, String avatar) {
        userMapper.createWithAvatar(userName,password,avatar);
    }
}
