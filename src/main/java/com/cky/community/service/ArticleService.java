package com.cky.community.service;

import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Article;

import java.util.List;

public interface ArticleService {

    void create(Article article);

    ArticleDto findById(int id);

    PaginationDto getArticleList(String search, int start, int size);

    PaginationDto getArticlesByUserId(int userId, int page, int size);

    List<ArticleDto> getRelatedArticles(ArticleDto articleDto);

    void createOrUpdate(Article article);

    void incViewCount(int id);

    void incCommentCount(int id);

    List<Article> findHotArticles();
}
