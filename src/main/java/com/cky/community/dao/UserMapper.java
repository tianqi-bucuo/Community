package com.cky.community.dao;

import com.cky.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {

    User findById(Integer id);

    void create(@Param("userName") String userName, @Param("password") String password);

    User login(@Param("userName") String userName, @Param("password") String password);

    List<User> getUserList();

    List<String> getUserNames();

    void createWithAvatar(@Param("userName") String userName, @Param("password")String password,
                          @Param("avatar") String avatar);

    void change(@Param("userName") String userName, @Param("password")String password,
                    @Param("avatar") String avatar, @Param("id") Integer id);
}
