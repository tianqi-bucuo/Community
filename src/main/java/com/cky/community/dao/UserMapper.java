package com.cky.community.dao;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    User findById(Integer id);

    void create(@Param("userName") String userName, @Param("password") String password);

    User login(@Param("userName") String userName, @Param("password") String password);

    List<User> getUserList();

}
