package com.cky.community.controller;

import com.cky.community.cache.TagCache;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/publish")
    public String publish(HttpSession session,Model model){
        if (session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(content)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "login";
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setTag(tag);
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
        model.addAttribute("id", article.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
}
