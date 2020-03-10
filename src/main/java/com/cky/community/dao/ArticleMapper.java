package com.cky.community.dao;

import com.cky.community.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getArticlesByUserId(@Param("userId") int userId,@Param("start") int start,@Param("size") int size);

    Article findById(int id);

    void create(Article article);

    List<Article> getArticleList(@Param("start") int start,@Param("size") int size);

    int totalCount();

    int count(int userId);

    void update(Article article);

    void incCommentCount(int id);

    void incViewCount(int id);

    void incLikeCount(int id);
}
