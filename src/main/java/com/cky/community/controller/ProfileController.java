package com.cky.community.controller;

import com.cky.community.dto.PaginationDTO;
import com.cky.community.entity.User;
import com.cky.community.service.ArticleService;
import com.cky.community.service.impl.ArticleServiceImpl;
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

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "1") int size,
                          HttpSession session){

        //获取当前用户信息
        if (session.getAttribute("user")==null){
            return "redirect:/";
        }
        User user = (User)session.getAttribute("user");
        int userId = user.getId();
        if ("articles".equals(action)){
            model.addAttribute("section","articles");
            model.addAttribute("sectionName","我的文章");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = articleService.getArticlesByUserId(userId,page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
