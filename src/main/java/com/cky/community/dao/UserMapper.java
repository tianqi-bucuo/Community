package com.cky.community.dao;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findById(int id);

    void create(User user);

}
