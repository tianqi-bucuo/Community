package com.cky.community.dao;

import com.cky.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void create(Comment comment);

    List<Comment> findByArticleId(int articleId);

    Comment findById(int id);

}
