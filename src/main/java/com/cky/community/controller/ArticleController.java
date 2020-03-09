package com.cky.community.controller;

import com.cky.community.dto.ArticleDto;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") int id,Model model){
        ArticleDto articleDto = articleService.findById(id);
        model.addAttribute("article",articleDto);
        return "article";
    }
}
