package com.cky.community.controller;

import com.cky.community.dto.ArticleDto;
import com.cky.community.entity.Article;
import com.cky.community.entity.User;
import com.cky.community.service.impl.ArticleServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PublishController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/publish")
    public String publish(HttpSession session){
        if (session.getAttribute("user") == null){
            return "login";
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Article article, HttpSession session , Model model,
                            @RequestParam(name = "id",required = false)Integer id){
        if (StringUtils.isBlank(article.getTitle())) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(article.getContent())) {
            model.addAttribute("error", "文章内容不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(article.getTitle())) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) session.getAttribute("user");
        article.setAuthorId(user.getId());
        article.setId(id);
        articleService.createOrUpdate(article);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") int id,
                       Model model) {
        ArticleDto article = articleService.findById(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        model.addAttribute("tag", article.getTag());
        model.addAttribute("id", id);
        return "publish";
    }
}
