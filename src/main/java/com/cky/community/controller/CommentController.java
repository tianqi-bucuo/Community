package com.cky.community.controller;

import com.cky.community.entity.Comment;
import com.cky.community.entity.User;
import com.cky.community.service.impl.ArticleServiceImpl;
import com.cky.community.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private ArticleServiceImpl articleService;

    //如果不加@ResponseBody注解，会出现找不到"comment"模板的异常(没有这个页面)
    @ResponseBody
    @PostMapping(value = "/comment")
    public void create(@RequestBody Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        commentService.create(comment,user);
        articleService.incCommentCount(comment.getArticleId());
    }
}
