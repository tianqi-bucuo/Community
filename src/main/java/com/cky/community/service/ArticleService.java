package com.cky.community.service;

import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Article;

public interface ArticleService {

    void create(Article article);

    ArticleDto findById(int id);

    PaginationDto getArticleList(int start, int size);

    PaginationDto getArticlesByUserId(int userId, int page, int size);

    void createOrUpdate(Article article);

    void incViewCount(int id);

    void incCommentCount(int id);

}
