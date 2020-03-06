package com.cky.community.controller;

import com.cky.community.entity.Article;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {

        List<Article> list = articleService.getArticleList();
        model.addAttribute("articleList",list);
        return "index";
    }
}
