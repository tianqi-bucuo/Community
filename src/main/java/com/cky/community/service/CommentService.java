package com.cky.community.service;

import com.cky.community.dto.CommentDto;
import com.cky.community.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {

    @Transactional
    void create(Comment comment);

    List<CommentDto> getCommentList(int articleId);
}
