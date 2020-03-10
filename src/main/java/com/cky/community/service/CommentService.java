package com.cky.community.service;

import com.cky.community.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

    @Transactional
    void create(Comment comment);

}
