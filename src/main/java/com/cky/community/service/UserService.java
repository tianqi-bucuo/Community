package com.cky.community.service;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    void userRegister(String userName, String password);

    User findById(int id);

    User login(String userName,String password);
}
