package com.cky.community.dao;

import com.cky.community.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    Article findById(int id);

    void create(Article article);

    List<Article> getArticleList(@Param("start") int start,@Param("size") int size);

    int count();
}
