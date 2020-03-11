package com.cky.community.service.impl;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.CommentMapper;
import com.cky.community.entity.Comment;
import com.cky.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public void create(Comment comment) {
        commentMapper.create(comment);
    }
}
