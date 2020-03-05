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
    public void userRegister(User user) {
        userMapper.insert(user);
    }

    @Override
    public User selectByPrimaryKey(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
