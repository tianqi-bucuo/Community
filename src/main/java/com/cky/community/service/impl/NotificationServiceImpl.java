package com.cky.community.service.impl;

import com.cky.community.dao.NotificationMapper;
import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class NotificationServiceImpl {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDto list(Integer userId, Integer page, Integer size) {

        PaginationDto<Notification> paginationDTO = new PaginationDto<>();

        Integer totalPage;

        Integer totalCount = notificationMapper.count(userId);

        if (totalCount==0){
            totalPage = 1;
        }else if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        Integer start = size * (page - 1);

        List<Notification> notifications = notificationMapper.getNotificationsByUserId(userId,start,size);

        if (notifications.size() == 0) {
            return paginationDTO;
        }

        paginationDTO.setData(notifications);
        return paginationDTO;
    }

    public Notification read(Integer id) {
        Notification notification = notificationMapper.findById(id);

        notification.setStatus(1);
        notificationMapper.updateStatusById(id);
        return notification;
    }

    public Integer unreadCount(Integer userId) {
        return notificationMapper.unreadCount(userId);
    }
}
