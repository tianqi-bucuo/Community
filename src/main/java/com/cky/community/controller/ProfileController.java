package com.cky.community.controller;

import com.cky.community.dto.PaginationDto;
import com.cky.community.entity.User;
import com.cky.community.service.impl.ArticleServiceImpl;
import com.cky.community.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "8") int size,
                          HttpSession session){
        User user = (User)session.getAttribute("user");
        //获取当前用户信息
        if (user==null){
            return "redirect:/";
        }
        if ("articles".equals(action)){
            model.addAttribute("section","articles");
            model.addAttribute("sectionName","我的文章");
            PaginationDto paginationDto = articleService.getArticlesByUserId(user.getId(), page, size);
            model.addAttribute("pagination", paginationDto);
        }else if ("replies".equals(action)){
            PaginationDto paginationDto = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("pagination", paginationDto);
            model.addAttribute("sectionName", "最新消息");
        }
        return "profile";
    }
}
