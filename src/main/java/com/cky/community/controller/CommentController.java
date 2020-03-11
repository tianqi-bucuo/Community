package com.cky.community.controller;

import com.cky.community.entity.Comment;
import com.cky.community.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping(value = "/comment")
    public String create(@RequestBody Comment comment){
        commentService.create(comment);
        return "redirect:/";
    }
}
