package com.cky.community.service.impl;

import com.cky.community.component.enums.NotificationStatusEnum;
import com.cky.community.component.enums.NotificationTypeEnum;
import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.CommentMapper;
import com.cky.community.dao.NotificationMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.dto.CommentDto;
import com.cky.community.entity.Article;
import com.cky.community.entity.Comment;
import com.cky.community.entity.Notification;
import com.cky.community.entity.User;
import com.cky.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public void create(Comment comment,User user) {

        Article article = articleMapper.findById(comment.getArticleId());
        commentMapper.create(comment);
        createNotify(comment, article.getAuthorId(), user.getUserName(), article.getTitle(),
                NotificationTypeEnum.REPLY_QUESTION, article.getId());
    }

    @Override
    public List<CommentDto> getCommentList(int articleId) {
        List<Comment> commentList = commentMapper.findByArticleId(articleId);
        if (commentList.size()==0){
            return new ArrayList<>();
        }
        //得到所有评论人的id并去重
        Set<Integer> userIds = commentList.stream().map(comment -> comment.getUserId()).collect(Collectors.toSet());

        Map<Integer,User> map = new HashMap<>();
        for(Integer userId:userIds){
            map.put(userId,userMapper.findById(userId));
        }
        //将评论和用户组合成CommentDto
        List<CommentDto> commentDtoList = commentList.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDto.setUser(map.get(comment.getUserId()));
            return commentDto;
        }).collect(Collectors.toList());
        return commentDtoList;
    }

    @Override
    public void incLikeCount(int commentId) {
        commentMapper.incLikeCount(commentId);
    }

    private void createNotify(Comment comment, Integer receiverId, String notifierName, String articleTitle, NotificationTypeEnum notificationType, Integer articleId) {
        if (receiverId == comment.getUserId()) {
            return;
        }
        Notification notification = new Notification();
        notification.setArticleId(articleId);
        notification.setNotifierId(comment.getUserId());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiverId(receiverId);
        notification.setNotifierName(notifierName);
        notification.setArticleTitle(articleTitle);
        notification.setCreateTime(new Date());
        notificationMapper.create(notification);
    }
}
