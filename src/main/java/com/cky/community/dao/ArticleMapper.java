package com.cky.community.dao;

import com.cky.community.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    Article findById(int id);

    void create(Article article);
}
