package com.cky.community.controller;

import com.cky.community.dto.ArticleDto;
import com.cky.community.dto.PaginationDTO;
import com.cky.community.entity.Article;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/")
    public String index(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "1") int size) {
        PaginationDTO pagination = articleService.getArticleList(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
