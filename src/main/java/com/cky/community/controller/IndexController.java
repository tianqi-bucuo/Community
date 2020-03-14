package com.cky.community.controller;

import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.Article;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private ArticleServiceImpl articleService;

    //方法参数中的page和size必须赋上默认值
    @GetMapping("/")
    public String index(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "8") int size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDto pagination = articleService.getArticleList(search,page,size);
        //得到热门文章
        List<Article> hotArticles =  articleService.findHotArticles();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search", search);
        model.addAttribute("hotArticles",hotArticles);
        return "index";
    }
}
