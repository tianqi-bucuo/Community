package com.cky.community.service;

import com.cky.community.entity.Article;

import java.util.List;

public interface ArticleService {

    void create(Article article);

    Article findById(int id);

    List<Article> getArticleList();
}
