package com.cky.community.service.impl;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Article;
import com.cky.community.entity.User;
import com.cky.community.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private UserMapper userMapper;

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
    public ArticleDto findById(int id) {
        Article article = articleMapper.findById(id);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article, articleDto);
        User user = userMapper.findById(article.getAuthorId());
        articleDto.setUser(user);
        return articleDto;
    }

    @Override
    public PaginationDto getArticleList(int page, int size) {

        PaginationDto paginationDto = new PaginationDto();
        int totalCount = articleMapper.totalCount();
        int totalPage;
        if (totalCount==0){
            totalPage = 1;
        }else if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size + 1;
        }
        paginationDto.setPagination(totalPage, page);

        if (page<1){
            page=1;
        }
        if (page> paginationDto.getTotalPage()){
            page = paginationDto.getTotalPage();
        }

        int start = (page-1)*size;
        List<Article> articles = articleMapper.getArticleList(start,size);
        List<ArticleDto> list = new ArrayList<>();

        for (Article article:articles){
            User user = userMapper.findById(article.getAuthorId());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDto.setUser(user);
            list.add(articleDto);
        }
        paginationDto.setArticles(list);
        return paginationDto;
    }

    @Override
    public PaginationDto getArticlesByUserId(int userId, int page, int size) {

        PaginationDto paginationDto = new PaginationDto();
        int totalCount = articleMapper.count(userId);
        int totalPage;
        if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size + 1;
        }
        paginationDto.setPagination(totalPage, page);

        if (page<1){
            page=1;
        }
        if (page> paginationDto.getTotalPage()){
            page = paginationDto.getTotalPage();
        }

        int start = (page-1)*size;
        List<Article> articles = articleMapper.getArticlesByUserId(userId,start,size);
        List<ArticleDto> list = new ArrayList<>();
        User user = userMapper.findById(userId);
        for (Article article:articles){
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDto.setUser(user);
            list.add(articleDto);
        }
        paginationDto.setArticles(list);
        return paginationDto;
    }

    @Override
    public List<ArticleDto> getRelatedArticles(ArticleDto queryDto) {
        if (StringUtils.isBlank(queryDto.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDto.getTag(), " ");
        String regexpTag = Arrays
                .stream(tags)
                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));
        Article article = new Article();
        article.setId(queryDto.getId());
        article.setTag(regexpTag);

        List<Article> questions = articleMapper.getRelatedArticles(article);
        List<ArticleDto> articleDtos = questions.stream().map(q -> {
            ArticleDto questionDTO = new ArticleDto();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return articleDtos;
    }

    @Override
    public void createOrUpdate(Article article) {
        if (article.getId()==null){
            //创建
            articleMapper.create(article);
        }else {
            //更新
            article.setUpdateTime(new Date());
            articleMapper.update(article);
        }
    }

    @Override
    public void incViewCount(int id) {
        articleMapper.incViewCount(id);
    }

    @Override
    public void incCommentCount(int id) {
        articleMapper.incCommentCount(id);
    }
}
