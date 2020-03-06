package com.cky.community.dao;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findById(int id);

    void create(@Param("userName") String userName, @Param("password") String password);

    User login(@Param("userName") String userName, @Param("password") String password);

}
