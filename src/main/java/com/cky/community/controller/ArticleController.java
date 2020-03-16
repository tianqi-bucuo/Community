package com.cky.community.controller;

import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.CommentDto;
import com.cky.community.service.impl.ArticleServiceImpl;
import com.cky.community.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") int articleId,Model model){
        ArticleDto articleDto = articleService.findById(articleId);
        //得到相关文章
        List<ArticleDto> relatedArticles = articleService.getRelatedArticles(articleDto);
        List<CommentDto> comments = commentService.getCommentList(articleId);
        model.addAttribute("relatedArticles",relatedArticles);
        model.addAttribute("article",articleDto);
        model.addAttribute("comments",comments);
        //累计阅读数
        articleService.incViewCount(articleId);
        return "article";
    }
}
