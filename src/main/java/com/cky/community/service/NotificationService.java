package com.cky.community.service;

import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Notification;

public interface NotificationService {

    public PaginationDto list(Integer userId, Integer page, Integer size);

    public Notification read(Integer id);

    public Integer unreadCount(Integer userId);

}
