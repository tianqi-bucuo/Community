package com.cky.community.service;

import com.cky.community.entity.Article;

public interface ArticleService {

    void create(Article article);

    Article findById(int id);
}
