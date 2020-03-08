package com.cky.community.service.impl;

import com.cky.community.dao.ArticleMapper;
import com.cky.community.dao.UserMapper;
import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.PaginationDTO;
import com.cky.community.entity.Article;
import com.cky.community.entity.User;
import com.cky.community.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    @Override
    public PaginationDTO getArticleList(int page,int size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = articleMapper.totalCount();
        int totalPage;
        if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size + 1;
        }
        paginationDTO.setPagination(totalPage, page);

        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
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
        paginationDTO.setArticles(list);
        return paginationDTO;
    }

    @Override
    public PaginationDTO getArticlesByUserId(int userId, int page, int size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        int totalCount = articleMapper.count(userId);
        int totalPage;
        if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size + 1;
        }
        paginationDTO.setPagination(totalPage, page);

        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
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
        paginationDTO.setArticles(list);
        return paginationDTO;
    }
}
