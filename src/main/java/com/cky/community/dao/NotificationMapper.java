package com.cky.community.dao;

import com.cky.community.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void create(Notification notification);

    Notification findById(Integer id);

    void updateStatusById(Integer id);

    Integer count(Integer userId);

    List<Notification> getNotificationsByUserId(@Param("userId") Integer userId, @Param("start") Integer start, @Param("size") Integer size);

    Integer unreadCount(Integer userId);
}
