package com.cky.community.dao;

import com.cky.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    void create(Comment comment);

    Comment findByArticleId(int articleId);

    Comment findById(int id);

}
