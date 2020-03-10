package com.cky.community.controller;

import com.cky.community.dto.PaginationDto;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @Autowired
    private ArticleServiceImpl articleService;

    //方法参数中的page和size必须赋上默认值
    @GetMapping("/")
    public String index(Model model,@RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "8") int size) {
        PaginationDto pagination = articleService.getArticleList(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
