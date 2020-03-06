package com.cky.community.service.impl;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.entity.Article;
import com.cky.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 用于注册用户
     * @param article
     */
    @Override
    public void create(Article article) {
        articleMapper.create(article);
    }

    @Override
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> getArticleList() {
        return null;
    }
}
