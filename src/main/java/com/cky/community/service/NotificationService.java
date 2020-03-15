package com.cky.community.service;

import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Notification;

public interface NotificationService {

    PaginationDto list(Integer userId, Integer page, Integer size);

    Notification read(Integer id);

    Integer unreadCount(Integer userId);

}
