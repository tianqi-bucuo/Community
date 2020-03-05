package com.cky.community.dao;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByToken(@Param("token") String token);

    void insert(User user);

    User selectByPrimaryKey(int id);
}
