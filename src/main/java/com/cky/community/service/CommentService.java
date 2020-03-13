package com.cky.community.service;

import com.cky.community.dto.CommentDto;
import com.cky.community.entity.Comment;
import com.cky.community.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {

    @Transactional
    void create(Comment comment,User user);

    List<CommentDto> getCommentList(int articleId);
}
