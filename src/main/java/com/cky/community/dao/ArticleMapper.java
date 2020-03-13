package com.cky.community.dao;

import com.cky.community.dto.ArticleQueryDto;
import com.cky.community.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> getArticlesByUserId(@Param("userId") Integer userId,@Param("start") Integer start,@Param("size") Integer size);

    Article findById(Integer id);

    void create(Article article);

    List<Article> getArticleList(@Param("start") Integer start,@Param("size") Integer size);

    List<Article> getRelatedArticles(Article article);

    Integer totalCount();

    Integer count(Integer userId);

    void update(Article article);

    void incCommentCount(Integer id);

    void incViewCount(Integer id);

    void incLikeCount(Integer id);

    int countBySearch(ArticleQueryDto articleQueryDto);

    List<Article> findBySearch(ArticleQueryDto articleQueryDto);

}
